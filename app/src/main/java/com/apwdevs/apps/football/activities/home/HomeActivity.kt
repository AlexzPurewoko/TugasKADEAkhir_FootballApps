package com.apwdevs.apps.football.activities.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.FavoritesFragment
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.MatchFragments
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.FragmentTeams
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        val receiver = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as List<TeamLeagueData>

        val mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, receiver)

        container.adapter = mSectionsPagerAdapter
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_match -> {
                    container.setCurrentItem(0, true)
                    true
                }
                R.id.menu_teams -> {
                    container.setCurrentItem(1, true)
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
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, private val leagues: List<TeamLeagueData>) :
        FragmentPagerAdapter(fm) {
        private val fragments = arrayListOf(
            MatchFragments.newInstance(leagues),
            FragmentTeams.newInstance(leagues),
            FavoritesFragment.newInstance(leagues)
        )

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = 3

    }
}
