package com.apwdevs.apps.football.activities.detailTeams

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerCarry
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamsAbout
import com.apwdevs.apps.football.activities.detailTeams.fragments.DetailTeamsFragment
import com.apwdevs.apps.football.activities.detailTeams.fragments.ListMemberFragment
import com.apwdevs.apps.football.activities.detailTeams.fragments.OverviewFragment
import com.apwdevs.apps.football.activities.detailTeams.presenter.AboutTeamsPresenter
import com.apwdevs.apps.football.activities.detailTeams.ui.AboutTeamsModel
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.database.TeamFavoriteData
import com.apwdevs.apps.football.database.database
import com.apwdevs.apps.football.utility.DialogShowHelper
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about_teams.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class AboutTeams : AppCompatActivity(), AboutTeamsModel {


    private lateinit var dialog: DialogShowHelper
    private lateinit var viewPagerContainer: ViewPager
    private lateinit var mViewPagerFragmentAdapter: ViewPagerFragmentAdapter
    private lateinit var tabLayout: TabLayout

    private var teams: TeamsAbout? = null
    private lateinit var leagues: MutableList<TeamLeagueData>
    private lateinit var players: List<TeamMemberShortData>
    private lateinit var recyclerDataSets: List<DetailRecyclerData>
    private lateinit var presenter: AboutTeamsPresenter

    private lateinit var teamBadges: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadiumName: TextView
    private lateinit var teamId: String
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_teams)
        dialog = DialogShowHelper(this)
        dialog.buildLoadingLayout()
        // initialization
        teamBadges = activity_aboutteams_teambadges
        teamName = activity_aboutteams_teamnames
        teamFormedYear = activity_aboutteams_teamformedyear
        teamStadiumName = activity_aboutteams_teamstadium
        tabLayout = activity_teamsabout_tablayout
        viewPagerContainer = activity_aboutteams_conteiner
        viewPagerContainer.offscreenPageLimit = 4
        ////////////////////
        activity_aboutteams_appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxAlpha = 1.0f
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()
            val retAlpha = maxAlpha - percentage
            teamBadges.alpha = retAlpha
            teamName.alpha = retAlpha
            teamFormedYear.alpha = retAlpha
            teamStadiumName.alpha = retAlpha


        }

        // init presenter
        teamId = intent.getStringExtra(ParameterClass.ID_SELECTED_TEAMS)
        leagues = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as MutableList<TeamLeagueData>
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = AboutTeamsPresenter(this, this, apiRepository, gson)
        setSupportActionBar(aboutteams_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.getDataBehaviour(teamId)
        activity_aboutteams_swiperefresh.onRefresh {
            presenter.getDataBehaviour(teamId)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        dialog.showDialog()
    }

    override fun hideLoading() {
        dialog.stopDialog()
    }

    override fun onLoadCancelled(msg: String) {
        activity_aboutteams_swiperefresh.isRefreshing = false
        alert(msg, "Error") {
            negativeButton("Okay") {
                it.dismiss()
                finish()
                //val pid = Process.myPid()
                //Handler(Looper.getMainLooper()).postDelayed({ Process.killProcess(pid) }, 1500)
            }
            iconResource = R.drawable.ic_report_problem
        }.show()
    }

    override fun onLoadFinished(
        team: TeamsAbout,
        players: List<TeamMemberShortData>,
        recyclerDataSets: List<DetailRecyclerData>,
        msg: String
    ) {
        this.teams = team
        this.players = players
        this.recyclerDataSets = recyclerDataSets

        Picasso.get().load(teams?.teamBadge).resize(150, 150).into(teamBadges)
        teamName.text = teams?.teamName
        teamStadiumName.text = teams?.stadiumName
        teamFormedYear.text = teams?.formedOnYear

        mViewPagerFragmentAdapter = ViewPagerFragmentAdapter(supportFragmentManager)
        viewPagerContainer.adapter = mViewPagerFragmentAdapter
        viewPagerContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                tabLayout.setScrollPosition(p0, p1, false)
            }

            override fun onPageSelected(p0: Int) {
                activity_aboutteams_appbar.setExpanded(true, true)
                tabLayout.getTabAt(p0)?.select()
            }

        })
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPagerContainer.setCurrentItem(tab?.position ?: 0, true)
            }

        })
        supportActionBar?.title = "About ${team.teamName}"
        favoriteState()
        setFavorite()
        activity_aboutteams_swiperefresh.isRefreshing = false
        activity_aboutteams_swiperefresh.snackbar(msg)
    }

    inner class ViewPagerFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment = when (p0) {
            0 -> OverviewFragment.newInstance(teams?.clubDescription ?: "Description is Unavailable")
            1 -> DetailTeamsFragment.newInstance(DetailRecyclerCarry(recyclerDataSets))
            else -> ListMemberFragment.newInstance(players, leagues, teamId)
        }

        override fun getCount(): Int = 3

    }

    private fun favoriteState() {
        if (teams == null) return
        database.use {
            val result = select(TeamFavoriteData.TABLE_TEAM_FAVORITE)
                .whereArgs(
                    "(${TeamFavoriteData.ID_TEAM} = {id})",
                    "id" to teams?.teamId!!
                )
            val favorites = result.parseList(classParser<TeamFavoriteData>())

            if (!favorites.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    TeamFavoriteData.TABLE_TEAM_FAVORITE,
                    TeamFavoriteData.ID_TEAM to teams?.teamId,
                    TeamFavoriteData.TEAM_NAME to teams?.teamName,
                    TeamFavoriteData.TEAM_LOGO to teams?.teamBadge
                )
            }
            viewPagerContainer.snackbar(ParameterClass.STRING_ADD_INTO_DATABASE).show()
        } catch (e: SQLiteConstraintException) {
            viewPagerContainer.snackbar(e.localizedMessage).show()

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    TeamFavoriteData.TABLE_TEAM_FAVORITE,
                    "(${TeamFavoriteData.ID_TEAM} = {id})",
                    "id" to teams?.teamId!!
                )
            }
            viewPagerContainer.snackbar(ParameterClass.STRING_REMOVE_FROM_DATABASE).show()
        } catch (e: SQLiteConstraintException) {
            viewPagerContainer.snackbar(e.localizedMessage).show()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
