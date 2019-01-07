package com.apwdevs.apps.football.activities.detailMatchs

import android.content.pm.ActivityInfo
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DataPropertyRecycler
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DetailMatchDataClass
import com.apwdevs.apps.football.activities.detailMatchs.dataController.TeamPropData
import com.apwdevs.apps.football.activities.detailMatchs.presenter.DetailMatchPresenter
import com.apwdevs.apps.football.activities.detailMatchs.ui.DetailMatchModel
import com.apwdevs.apps.football.activities.detailMatchs.ui.adapter.DetailCardViewHolder
import com.apwdevs.apps.football.activities.detailMatchs.ui.adapter.DetailMatchRecyclerAdapter
import com.apwdevs.apps.football.activities.home.HomeActivity
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.database.MatchFavoriteData
import com.apwdevs.apps.football.database.database
import com.apwdevs.apps.football.utility.DialogShowHelper
import com.apwdevs.apps.football.utility.MyDate
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.content_detail_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class DetailMatchActivity : AppCompatActivity(), DetailMatchModel {


    // data section
    private lateinit var matchDetails: DetailMatchDataClass
    private lateinit var teamProps: List<TeamPropData>
    private lateinit var dataProps: MutableList<DataPropertyRecycler>
    // upper layout section
    private lateinit var homeLogo: ImageView
    private lateinit var awayLogo: ImageView
    private lateinit var scoreHome: TextView
    private lateinit var scoreAway: TextView
    private lateinit var nameHome: TextView
    private lateinit var nameAway: TextView
    private lateinit var dateText: TextView
    private lateinit var timeText: TextView

    // recyclersection
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<DetailCardViewHolder>

    // other utility
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private val dialog: DialogShowHelper = DialogShowHelper(this)
    private lateinit var idMatch: String
    private lateinit var idLeague: String
    private lateinit var nameLeague: String
    private lateinit var items: LeagueResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setSupportActionBar(activity_detail_match_toolbar)
        prepareLayout()
        idMatch = intent.getStringExtra(ParameterClass.ID_EVENT_MATCH_SELECTED)
        idLeague = intent.getStringExtra(ParameterClass.ID_SELECTED_LEAGUE_KEY)
        nameLeague = intent.getStringExtra(ParameterClass.NAME_LEAGUE_KEY)
        items = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        activity_detail_match_swiperefresh.setColorSchemeColors(
            getColors(R.color.colorAccent),
            getColors(android.R.color.holo_green_light),
            getColors(android.R.color.holo_orange_light),
            getColors(android.R.color.holo_red_light)
        )
        val apiRepository = ApiRepository()
        val gson = Gson()
        val presenter = DetailMatchPresenter(this, apiRepository, this, gson)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = nameLeague
        presenter.getData(idMatch)
        activity_detail_match_swiperefresh.onRefresh {
            presenter.getData(idMatch)
        }

    }

    override fun onResume() {
        super.onResume()
        favoriteState()
    }

    private fun prepareLayout() {
        homeLogo = content_match_detail_id_home_logoteam
        awayLogo = content_match_detail_id_away_logoteam
        scoreHome = content_match_detail_id_teamscore_left
        scoreAway = content_match_detail_id_teamscore_right
        nameHome = content_match_detail_id_teamname_left
        nameAway = content_match_detail_id_teamname_right
        dateText = content_match_detail_id_date
        timeText = content_match_detail_id_time

        recyclerView = content_match_detail_id_recyclerlist
        dialog.buildLoadingLayout()
    }

    private fun getColors(colorId: Int): Int {
        return ContextCompat.getColor(this, colorId)
    }

    override fun showLoading() {
        dialog.showDialog()
    }

    override fun hideLoading() {
        dialog.stopDialog()
    }

    override fun onSuccessLoadingData(
        matchData: DetailMatchDataClass,
        teamProps: List<TeamPropData>,
        dataRecycler: MutableList<DataPropertyRecycler>
    ) {
        activity_detail_match_swiperefresh.isRefreshing = false
        this.matchDetails = matchData
        this.teamProps = teamProps
        this.dataProps = dataRecycler
        adapter = DetailMatchRecyclerAdapter(dataProps)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        nameHome.text = teamProps[0].strTeam
        nameAway.text = teamProps[1].strTeam
        scoreHome.text = matchDetails.intHomeScore ?: "-"
        scoreAway.text = matchDetails.intAwayScore ?: "-"
        Picasso.get().load(teamProps[0].strTeamBadge).resize(100, 100).into(homeLogo)
        Picasso.get().load(teamProps[1].strTeamBadge).resize(100, 100).into(awayLogo)

        val currentCalendar = MyDate.getCalendarInGMT7(matchDetails.timeEvent, matchDetails.dateEvent, "yyyy-MM-dd")
        dateText.text = MyDate.getDateFromCalendar(currentCalendar)//getDate(matchDetails.dateEvent, "yyyy-MM-dd")
        timeText.text = MyDate.getTimeFromCalendar(currentCalendar)//MyDate.getTimeInGMT7(matchDetails.timeEvent)
    }

    override fun onFailedLoadingData(what: String) {
        alert(what, "Error") {
            iconResource = R.drawable.ic_report_problem
            negativeButton("Quit!") {
                it.dismiss()
                this@DetailMatchActivity.finish()
            }
        }.show()
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


    private fun favoriteState() {
        database.use {
            val result = select(MatchFavoriteData.TABLE_MATCH_FAVORITE)
                .whereArgs(
                    "(${MatchFavoriteData.ID_LEAGUE} = {id} AND ${MatchFavoriteData.ID_EVENT} = {event})",
                    "id" to idLeague,
                    "event" to idMatch
                )
            val favorites = result.parseList(classParser<MatchFavoriteData>())

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
                    MatchFavoriteData.TABLE_MATCH_FAVORITE,
                    MatchFavoriteData.ID_LEAGUE to idLeague,
                    MatchFavoriteData.ID_EVENT to idMatch,
                    MatchFavoriteData.LEAGUE_NAME to nameLeague,
                    MatchFavoriteData.DATE_EVENT to matchDetails.dateEvent,
                    MatchFavoriteData.TIME_EVENT to matchDetails.timeEvent,
                    MatchFavoriteData.HOME_TEAM to matchDetails.strHomeTeam,
                    MatchFavoriteData.HOME_SCORE to matchDetails.intHomeScore?.toInt(),
                    MatchFavoriteData.AWAY_TEAM to matchDetails.strAwayTeam,
                    MatchFavoriteData.AWAY_SCORE to matchDetails.intAwayScore?.toInt()
                )
            }
            recyclerView.snackbar(ParameterClass.STRING_ADD_INTO_DATABASE).show()
        } catch (e: SQLiteConstraintException) {
            recyclerView.snackbar(e.localizedMessage).show()

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    MatchFavoriteData.TABLE_MATCH_FAVORITE,
                    "(${MatchFavoriteData.ID_LEAGUE} = {id} AND ${MatchFavoriteData.ID_EVENT} = {event})",
                    "id" to idLeague,
                    "event" to idMatch
                )
            }
            recyclerView.snackbar(ParameterClass.STRING_REMOVE_FROM_DATABASE).show()
        } catch (e: SQLiteConstraintException) {
            recyclerView.snackbar(e.localizedMessage).show()
        }
    }



    override fun onBackPressed() {
        finish()
        startActivity(
            intentFor<HomeActivity>(
                ParameterClass.LIST_LEAGUE_DATA to items.leagues
            ).clearTask()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
