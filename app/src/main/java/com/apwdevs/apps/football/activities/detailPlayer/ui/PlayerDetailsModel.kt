package com.apwdevs.apps.football.activities.detailPlayer.ui

import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayersDetailData
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData

interface PlayerDetailsModel {
    fun showLoading()
    fun hideLoading()
    fun onDataNotLoaded(msgWhat: String)
    fun onDataLoadFinished(
        playerData: PlayersDetailData,
        recyclerData: MutableList<DetailRecyclerData>,
        msgWhat: String
    )
}