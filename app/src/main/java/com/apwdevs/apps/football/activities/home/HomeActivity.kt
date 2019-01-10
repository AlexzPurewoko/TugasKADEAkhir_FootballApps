package com.apwdevs.apps.football.activities.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.FavoritesFragment
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.MatchFragments
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.FragmentTeams
import com.apwdevs.apps.football.activities.home.homeUtility.CustomSearchView
import com.apwdevs.apps.football.activities.home.homeUtility.FragmentHomeCallback
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        val receiver = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as List<TeamLeagueData>

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, receiver)

        val collapsing = collapse_toolbar
        collapsing.isTitleEnabled = false
        toolbar.title = title
        container.adapter = mSectionsPagerAdapter
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_match -> {
                    container.setCurrentItem(0, true)
                    true
                }
                R.id.menu_teams -> {
                    container.setCurrentItem(1, true)
                    val fragment = mSectionsPagerAdapter.getItem(container.currentItem)
                    if (container.currentItem == 1) {
                        val k: FragmentTeams = fragment as FragmentTeams
                        val i: FragmentHomeCallback = k
                        i.transactionData("Hanya Coba - coba saja.... wkwkwkwk :)")
                    }
                    true
                }
                else -> {
                    container.setCurrentItem(2, true)
                    true
                }
            }

        }
        container.offscreenPageLimit = 10
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                home_appbar.setExpanded(true, true)
                bottom_navigation.selectedItemId = when (p0) {
                    0 -> R.id.menu_match
                    1 -> R.id.menu_teams
                    else -> R.id.favorite
                }
            }

        })
        //container.offscreenPageLimit = 3
        container.setCurrentItem(0, true)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Football"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: CustomSearchView? = null
        if (searchItem != null)
            searchView = searchItem.actionView as CustomSearchView

        searchView?.queryHint = when (container.currentItem) {
            0 -> "Cari Match..."
            1 -> "Cari Team..."
            2 -> "Temukan Match/Team Favorit anda"
            else -> "Search..."
        }
        searchView?.onSearchCallback = object : CustomSearchView.CustomSearchViewCallback {
            override fun onActionViewCollapsed() {
                // force to collapse back the toolbar
                val previousParams = toolbar.layoutParams
                val previousCollapseParams = collapse_toolbar.layoutParams
                val collapseParams: CollapsingToolbarLayout.LayoutParams = CollapsingToolbarLayout.LayoutParams(
                    previousParams.width,
                    previousParams.height
                )

                val collapseModeCollapseParams: AppBarLayout.LayoutParams = AppBarLayout.LayoutParams(
                    previousCollapseParams
                )
                collapseParams.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                collapseModeCollapseParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or
                        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                toolbar.layoutParams = collapseParams
                collapse_toolbar.layoutParams = collapseModeCollapseParams
                toolbar.requestLayout()
                collapse_toolbar.requestLayout()
                home_appbar.requestLayout()

                // broadcast a callback
                getListener().onActionViewCollapsed()
            }

            override fun onActionViewExpanded() {
                // force to always expand the toolbar
                val previousParams = toolbar.layoutParams
                val previousCollapseParams = collapse_toolbar.layoutParams
                val expandMode: CollapsingToolbarLayout.LayoutParams = CollapsingToolbarLayout.LayoutParams(
                    previousParams
                )

                val expandModeCollapseParams: AppBarLayout.LayoutParams = AppBarLayout.LayoutParams(
                    previousCollapseParams
                )
                expandModeCollapseParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                expandMode.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                toolbar.layoutParams = expandMode
                collapse_toolbar.layoutParams = expandModeCollapseParams
                toolbar.requestLayout()
                collapse_toolbar.requestLayout()
                home_appbar.requestLayout()

                //broadcast a callback
                getListener().onActionViewExpanded()
            }

            override fun onDetachedFromWindow() {
                //toast("onDetachedFromWindow()")
                getListener().onDetachedFromWindow()
            }

        }
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean = getListener().onQueryTextSubmit(p0)

            override fun onQueryTextChange(p0: String?): Boolean = getListener().onQueryTextChange(p0)

        })
        return super.onCreateOptionsMenu(menu)
    }

    fun getListener(): FragmentHomeCallback =
        mSectionsPagerAdapter.getItem(container.currentItem) as FragmentHomeCallback

    inner class SectionsPagerAdapter(fm: FragmentManager, leagues: List<TeamLeagueData>) :
        FragmentPagerAdapter(fm) {
        private val fragments: ArrayList<Fragment> = arrayListOf(
            MatchFragments.newInstance(leagues),
            FragmentTeams.newInstance(leagues),
            FavoritesFragment.newInstance(leagues)
        )

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = 3

    }
}
