package com.apwdevs.apps.football.activities.detailPlayer.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailPlayer.apiResponse.GetPlayerDetails
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayerDetailsDataResponse
import com.apwdevs.apps.football.activities.detailPlayer.ui.PlayerDetailsModel
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.dataController.PropertyRecyclerType
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.CekKoneksi
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.*

class PlayerDetailsPresenter(
    private val ctx: Context,
    private val apiRepository: ApiRepository,
    private val model: PlayerDetailsModel,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    private var playerDetails: PlayerDetailsDataResponse? = null
    private var msg: String? = null
    private val recyclerData: MutableList<DetailRecyclerData> = mutableListOf()
    fun getPlayerDetails(playerId: String) {
        model.showLoading()
        GlobalScope.launch(contextPool.main) {

            val koneksi = if (isTesting) true else CekKoneksi.isConnected(ctx).await()
            if (koneksi) {

                if (getDetails(playerId).await()) {
                    // success then load the recycler data
                    getRecyclerData()
                } else {
                    msg = "Cannot establish player data from server\nSorry :("
                }

            } else {
                msg = "Connection lost!, please enable you connection first\n:("
            }

            if (isTesting) Thread.sleep(1000)
            else delay(1000)

            model.hideLoading()
            if (msg != null)
                model.onDataNotLoaded(msg!!)
            else {
                model.onDataLoadFinished(playerDetails?.players!![0], recyclerData)
            }

        }
    }

    fun getDetails(playerId: String): Deferred<Boolean> = GlobalScope.async {
        playerDetails = gson.fromJson(
            apiRepository.doRequest(GetPlayerDetails.getDetails(playerId)).await(),
            PlayerDetailsDataResponse::class.java
        )
        playerDetails == null
    }

    fun getRecyclerData() {
        val players = playerDetails?.players!![0]
        recyclerData.clear()
        recyclerData.add(DetailRecyclerData("More", null, PropertyRecyclerType.PROPERTY_INDEPENDENT))
        recyclerData.add(
            DetailRecyclerData(
                "Nationality",
                players.nationality,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerData.add(
            DetailRecyclerData(
                "Born On",
                players.playerBorn,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerData.add(
            DetailRecyclerData(
                "Birth Location",
                players.birthLocation,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerData.add(
            DetailRecyclerData(
                "Gender",
                players.playerGender,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )


        recyclerData.add(DetailRecyclerData("Social Media", null, PropertyRecyclerType.PROPERTY_INDEPENDENT))
        if (players.facebook != null) recyclerData.add(
            DetailRecyclerData(
                "Facebook",
                players.facebook,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        if (players.twitter != null) recyclerData.add(
            DetailRecyclerData(
                "Twitter",
                players.twitter,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        if (players.youtube != null) recyclerData.add(
            DetailRecyclerData(
                "Youtube",
                players.youtube,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        if (players.website != null) recyclerData.add(
            DetailRecyclerData(
                "Website",
                players.website,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        if (players.instagram != null) recyclerData.add(
            DetailRecyclerData(
                "Instagram",
                players.instagram,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
    }
}