package com.apwdevs.apps.football.activities.detailPlayer.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailPlayer.apiResponse.GetPlayerDetails
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayerDetailsDataResponse
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayersDetailData
import com.apwdevs.apps.football.activities.detailPlayer.ui.PlayerDetailsModel
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.dataController.PropertyRecyclerType
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.AvailableDataUpdates
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.apwdevs.apps.football.utility.ResultConnection
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.*

class PlayerDetailsPresenter(
    private val ctx: Context,
    private val apiRepository: ApiRepository,
    private val model: PlayerDetailsModel,
    private val gson: Gson,
    private val enableCaching: Boolean = true,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    private var playerDetails: PlayerDetailsDataResponse? = null
    private var msg: String? = null
    private val recyclerData: MutableList<DetailRecyclerData> = mutableListOf()
    var countUserRefresh = 0

    fun getPlayerDetails(playerId: String) {
        model.showLoading()
        GlobalScope.launch(contextPool.main) {
            val cachePlayers = File(ctx.cacheDir, "detail_player$playerId")
            val preventUpdate =
                AvailableDataUpdates.isAvailable(mutableListOf(cachePlayers), isTesting, countUserRefresh++).await()
            var isSuccess = false
            if (preventUpdate.preventToUpdate) {

                    if (!getPlayerDetailsFromInternet(playerId).await()) {
                        // save into file --> cacheFilesTeams
                        val fstream = FileOutputStream(cachePlayers)
                        val ostream = ObjectOutputStream(fstream)
                        ostream.writeObject(playerDetails?.players!!)
                        ostream.flush()
                        fstream.flush()
                        ostream.close()
                        fstream.close()
                        msg = preventUpdate.msg
                        isSuccess = true
                    } else {
                        msg = "Yahhh... Error saat ngambil data dari server... maaf yaa"
                    }
            } else {
                if (preventUpdate.enumResult == ResultConnection.CACHE_IS_AVAIL) {
                    // read from --> cacheFilesTeams
                    val fstream = FileInputStream(cachePlayers)
                    val istream = ObjectInputStream(fstream)
                    playerDetails = PlayerDetailsDataResponse(istream.readObject() as List<PlayersDetailData>)
                    istream.close()
                    fstream.close()
                    getRecyclerData()
                    isSuccess = true
                }
                msg = preventUpdate.msg
            }


            if (isTesting) Thread.sleep(1000)
            else delay(1000)
            model.hideLoading()
            if (!isSuccess)
                model.onDataNotLoaded(msg!!)
            else {
                model.onDataLoadFinished(playerDetails?.players!![0], recyclerData, msg!!)
            }
        }
    }

    fun getPlayerDetailsFromInternet(playerId: String): Deferred<Boolean> = GlobalScope.async {
            if (getDetails(playerId).await()) {
                // success then load the recycler data
                getRecyclerData()
                false
            } else {
                msg = "Cannot establish player data from server\nSorry :("
                true
            }
    }

    fun getDetails(playerId: String): Deferred<Boolean> = GlobalScope.async {
        playerDetails = gson.fromJson(
            apiRepository.doRequest(GetPlayerDetails.getDetails(playerId)).await(),
            PlayerDetailsDataResponse::class.java
        )
        playerDetails != null
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