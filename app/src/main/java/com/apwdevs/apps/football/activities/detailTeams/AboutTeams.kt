package com.apwdevs.apps.football.activities.detailTeams

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
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
import com.apwdevs.apps.football.activities.home.HomeActivity
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.DialogShowHelper
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about_teams.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor

class AboutTeams : AppCompatActivity(), AboutTeamsModel {


    private lateinit var dialog: DialogShowHelper
    private lateinit var viewPagerContainer: ViewPager
    private lateinit var mViewPagerFragmentAdapter: ViewPagerFragmentAdapter
    private lateinit var tabLayout: TabLayout

    private lateinit var teams: TeamsAbout
    private lateinit var leagues: MutableList<TeamLeagueData>
    private lateinit var players: List<TeamMemberShortData>
    private lateinit var recyclerDataSets: List<DetailRecyclerData>
    private lateinit var presenter: AboutTeamsPresenter

    private lateinit var teamBadges: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadiumName: TextView
    private lateinit var teamId: String
    /*private val targetBAckgroundAppBar: Target = object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            activity_aboutteams_appbar.background = BitmapDrawable(resources, bitmap)
        }

    }*/

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

        // init presenter
        teamId = intent.getStringExtra(ParameterClass.ID_SELECTED_TEAMS)
        leagues = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as MutableList<TeamLeagueData>
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = AboutTeamsPresenter(this, this, apiRepository, gson)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.getDataBehaviour(teamId)
    }

    override fun showLoading() {
        dialog.showDialog()
    }

    override fun hideLoading() {
        dialog.stopDialog()
    }

    override fun onLoadCancelled(msg: String) {
        alert(msg, "Error") {
            negativeButton("Okay") {
                it.dismiss()
                finish()
                val pid = Process.myPid()
                Handler(Looper.getMainLooper()).postDelayed({ Process.killProcess(pid) }, 1500)
            }
            iconResource = R.drawable.ic_report_problem
        }.show()
    }

    override fun onLoadFinished(
        team: TeamsAbout,
        players: List<TeamMemberShortData>,
        recyclerDataSets: List<DetailRecyclerData>
    ) {
        this.teams = team
        this.players = players
        this.recyclerDataSets = recyclerDataSets

        Picasso.get().load(teams.teamBadge).resize(150, 150).into(teamBadges)
        //Picasso.get().load(teams.stadiumImage).into(targetBAckgroundAppBar)
        teamName.text = teams.teamName
        teamStadiumName.text = teams.stadiumName
        teamFormedYear.text = teams.formedOnYear

        mViewPagerFragmentAdapter = ViewPagerFragmentAdapter(supportFragmentManager)
        viewPagerContainer.adapter = mViewPagerFragmentAdapter
        viewPagerContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                tabLayout.setScrollPosition(p0, p1, false)
            }

            override fun onPageSelected(p0: Int) {
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
    }

    inner class ViewPagerFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment = when (p0) {
            0 -> OverviewFragment.newInstance(teams.clubDescription ?: "Description is Unavailable")
            1 -> DetailTeamsFragment.newInstance(DetailRecyclerCarry(recyclerDataSets))
            else -> ListMemberFragment.newInstance(players, leagues, teamId)
        }

        override fun getCount(): Int = 3

    }

    override fun onBackPressed() {
        finish()
        startActivity(
            intentFor<HomeActivity>(
                ParameterClass.LIST_LEAGUE_DATA to leagues
            ).clearTask()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
