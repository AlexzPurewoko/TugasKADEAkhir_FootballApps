package com.apwdevs.apps.football.activities.detailPlayer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayersDetailData
import com.apwdevs.apps.football.activities.detailPlayer.presenter.PlayerDetailsPresenter
import com.apwdevs.apps.football.activities.detailPlayer.ui.PlayerDetailsModel
import com.apwdevs.apps.football.activities.detailTeams.AboutTeams
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.fragments.adapter.DetailTeamRA
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.DialogShowHelper
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_details.*
import kotlinx.android.synthetic.main.content_player_details.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

class PlayerDetails : AppCompatActivity(), PlayerDetailsModel {


    private lateinit var playerImage: ImageView
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playerDescription: TextView
    private lateinit var moreDetailsRecycler: RecyclerView
    private lateinit var recyclerAdapter: DetailTeamRA

    private lateinit var leagues: List<TeamLeagueData>
    private lateinit var playerId: String
    private lateinit var teamId: String

    private val dialog: DialogShowHelper = DialogShowHelper(this)
    private lateinit var presenter: PlayerDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        setSupportActionBar(player_details_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dialog.buildLoadingLayout()
        initializeMemberLayout()
        val apiRepository = ApiRepository()
        val gson = Gson()

        leagues = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as List<TeamLeagueData>
        teamId = intent.getStringExtra(ParameterClass.ID_SELECTED_TEAMS)
        playerId = intent.getStringExtra(ParameterClass.ID_SELECTED_PLAYERS)

        presenter = PlayerDetailsPresenter(this, apiRepository, this, gson)
        presenter.getPlayerDetails(playerId)
    }

    private fun initializeMemberLayout() {
        playerImage = player_details_imagephotos
        playerHeight = player_details_height
        playerWeight = player_details_weight
        playerPosition = player_details_player_position
        playerDescription = player_details_playerdetails
        moreDetailsRecycler = player_details_adapter

    }

    override fun showLoading() {
        dialog.showDialog()
    }

    override fun hideLoading() {
        dialog.stopDialog()
    }

    override fun onDataNotLoaded(msgWhat: String) {
        alert(msgWhat, "Error :(") {
            iconResource = R.drawable.ic_report_problem
            negativeButton("Quit") {
                it.dismiss()
                this@PlayerDetails.finish()
                val pid = Process.myPid()
                Handler(Looper.getMainLooper()).postDelayed({ Process.killProcess(pid) }, 1200)
            }
        }
    }

    override fun onDataLoadFinished(playerData: PlayersDetailData, recyclerData: MutableList<DetailRecyclerData>) {
        Picasso.get().load(playerData.playerPhotos).into(playerImage)
        playerWeight.text = playerData.playerWeight
        playerHeight.text = playerData.playerHeight
        playerPosition.text = playerData.playerPosition
        playerDescription.text = playerData.playerDescription

        recyclerAdapter = DetailTeamRA(recyclerData)
        moreDetailsRecycler.layoutManager = LinearLayoutManager(this)
        moreDetailsRecycler.adapter = recyclerAdapter
    }

    override fun onBackPressed() {
        startActivity(
            intentFor<AboutTeams>(
                ParameterClass.ID_SELECTED_TEAMS to teamId,
                ParameterClass.LIST_LEAGUE_DATA to leagues
            ).clearTop().clearTask()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
