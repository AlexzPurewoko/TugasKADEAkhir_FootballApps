package com.apwdevs.apps.football.activities.detailMatchs.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailMatchs.apiRequest.GetMatchDetailDataFromApi
import com.apwdevs.apps.football.activities.detailMatchs.dataController.*
import com.apwdevs.apps.football.activities.detailMatchs.ui.DetailMatchModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.AvailableDataUpdates
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.apwdevs.apps.football.utility.ResultConnection
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.*

class DetailMatchPresenter(
    private val ctx: Context,
    private val apiRepository: ApiRepository,
    private val model: DetailMatchModel,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    var data: DetailMatchResponse? = null
    var msg: String? = null
    var dataTeam: MutableList<TeamPropData>? = null
    var recyclerData: MutableList<DataPropertyRecycler>? = null
    var countUserRefresh = 0

    fun getData(eventId: String) {
        model.showLoading()
        GlobalScope.launch(contextPool.main) {
            val cacheTeams = File(ctx.cacheDir, "teams_event$eventId")
            val cacheData = File(ctx.cacheDir, "data_event$eventId")
            val preventUpdate =
                AvailableDataUpdates.isAvailable(
                    mutableListOf(cacheData, cacheTeams),
                    isTesting,
                    countUserRefresh++,
                    contextPool
                )
                    .await()
            var isSuccess = false
            if (preventUpdate.preventToUpdate) {
                if (!getDataFromServer(eventId).await()) {
                    // save into file --> cacheTeams
                    var fstream = FileOutputStream(cacheTeams)
                    var ostream = ObjectOutputStream(fstream)
                    ostream.writeObject(dataTeam!!)
                    ostream.flush()
                    fstream.flush()
                    ostream.close()
                    fstream.close()

                    // save into file --> cacheData
                    fstream = FileOutputStream(cacheData)
                    ostream = ObjectOutputStream(fstream)
                    ostream.writeObject(data!!)
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
                if (preventUpdate.enumResult == ResultConnection.IN_TESTING_MODE) {
                    data = DetailMatchPresenterImpl.getData(eventId)
                    recyclerData = getDataRecycler(data?.events!![0])
                    dataTeam = DetailMatchPresenterImpl.getTeamDataInMatch(data!!)
                    isSuccess = true
                } else if (preventUpdate.enumResult == ResultConnection.CACHE_IS_AVAIL) {
                    // read from --> cacheTeams
                    var fstream = FileInputStream(cacheTeams)
                    var istream = ObjectInputStream(fstream)
                    dataTeam = istream.readObject() as MutableList<TeamPropData>?
                    istream.close()
                    fstream.close()

                    // save into file --> cacheData
                    fstream = FileInputStream(cacheData)
                    istream = ObjectInputStream(fstream)
                    data = istream.readObject() as DetailMatchResponse?
                    istream.close()
                    fstream.close()
                    recyclerData = getDataRecycler(data?.events!![0])
                    isSuccess = true
                }
                msg = preventUpdate.msg
            }


            if (isTesting)
                Thread.sleep(1500)
            model.hideLoading()
            if (!isSuccess) {
                model.onFailedLoadingData(msg!!)
            } else {
                model.onSuccessLoadingData(data?.events!![0], dataTeam?.toList()!!, recyclerData!!, msg!!)
            }
        }
    }

    fun getDataFromServer(eventId: String): Deferred<Boolean> = GlobalScope.async(contextPool.main) {
        data = gson.fromJson(
                apiRepository.doRequest(GetMatchDetailDataFromApi.getDetailMatchURL(eventId)).await(),
                DetailMatchResponse::class.java
            )
        if (data != null) {
                val idHome = data?.events!![0].idHomeTeam
                val idAway = data?.events!![0].idAwayTeam
                val dataTeamHome = gson.fromJson(
                    apiRepository.doRequest(GetMatchDetailDataFromApi.getImageClubURL(idHome!!)).await(),
                    TeamPropDataResponse::class.java
                )
                val dataTeamAway = gson.fromJson(
                    apiRepository.doRequest(GetMatchDetailDataFromApi.getImageClubURL(idAway!!)).await(),
                    TeamPropDataResponse::class.java
                )
                dataTeam = mutableListOf()
                dataTeam?.clear()
                dataTeam?.add(dataTeamHome.teams[0])
                dataTeam?.add(dataTeamAway.teams[0])
                recyclerData = getDataRecycler(data?.events!![0])
        }
        data == null
    }

    fun getDataRecycler(data: DetailMatchDataClass): MutableList<DataPropertyRecycler> {
        val ret: MutableList<DataPropertyRecycler> = mutableListOf()
        ret.clear()
        ret.add(DataPropertyRecycler(true, "Statistics", null, null))
        ret.add(DataPropertyRecycler(false, "Goals", data.intHomeScore, data.intAwayScore))
        ret.add(
            DataPropertyRecycler(
                false,
                "Goal Details",
                buildPart(data.strHomeGoalDetails, ';', '\n'),
                buildPart(data.strAwayGoalDetails, ';', '\n')
            )
        )
        ret.add(DataPropertyRecycler(false, "Shots", data.intHomeShots, data.intAwayShots))
        ret.add(
            DataPropertyRecycler(
                false,
                "Yellow Cards",
                buildPart(data.strHomeYellowCards, ';', '\n'),
                buildPart(data.strAwayYellowCards, ';', '\n')
            )
        )
        ret.add(
            DataPropertyRecycler(
                false,
                "Red Cards",
                buildPart(data.strHomeRedCards, ';', '\n'),
                buildPart(data.strAwayRedCards, ';', '\n')
            )
        )

        ret.add(DataPropertyRecycler(true, "Lineups", null, null))
        ret.add(
            DataPropertyRecycler(
                false,
                "GoalKeeper",
                buildPart(data.strHomeLineupGoalkeeper, ';', '\n'),
                data.strAwayLineupGoalkeeper
            )
        )
        ret.add(
            DataPropertyRecycler(
                false,
                "Defense",
                buildPart(data.strHomeLineupDefense, ';', '\n'),
                buildPart(data.strAwayLineupGoalkeeper, ';', '\n')
            )
        )
        ret.add(
            DataPropertyRecycler(
                false,
                "MidField",
                buildPart(data.strHomeLineupMidfield, ';', '\n'),
                data.strAwayLineupMidfield
            )
        )
        ret.add(
            DataPropertyRecycler(
                false,
                "Forward",
                buildPart(data.strHomeLineupForward, ';', '\n'),
                buildPart(data.strAwayLineupForward, ';', '\n')
            )
        )
        ret.add(
            DataPropertyRecycler(
                false,
                "Subtitues",
                buildPart(data.strHomeLineupSubstitutes, ';', '\n'),
                buildPart(data.strAwayLineupSubstitutes, ';', '\n')
            )
        )

        return ret

    }

    private fun buildPart(str: String?, s: Char, c: Char): String? {
        if (str == null) return null
        val buffer = StringBuffer()
        for (i in str) {
            if (i == s)
                buffer.append(c)
            else
                buffer.append(i)
        }
        return buffer.toString()
    }
}