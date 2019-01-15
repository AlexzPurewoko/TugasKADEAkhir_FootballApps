package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.apiRepository.GetTeamList
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamDataResponse
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.FragmentTeamsModel
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

class FragmentTeamsPresenter(
    private val ctx: Context,
    private val view: FragmentTeamsModel,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {


    private var msg: String? = null
    private var data: TeamDataResponse? = null
    var countUserRefresh = 0
    private fun buildToURI(name: String): String {
        val sbuf = StringBuffer()
        for (i in name) {
            if (i == ' ')
                sbuf.append("%20")
            else
                sbuf.append(i)
        }
        return sbuf.toString()
    }


    fun getAllTeamsList(leagueName: String) {
        view.showLoading()
        val leagueInURI: String = buildToURI(leagueName)
        val cacheData = File(ctx.cacheDir, "team_list_league$leagueInURI")
        GlobalScope.launch(contextProvider.main) {
            val preventUpdate =
                AvailableDataUpdates.isAvailable(
                    mutableListOf(cacheData),
                    isTesting,
                    countUserRefresh++,
                    contextProvider
                ).await()
            var isSuccess = false
            if (preventUpdate.preventToUpdate) {

                if (!getAllTeamsListFromServer(leagueInURI).await()) {
                    // save into file --> cacheData
                    val fstream = FileOutputStream(cacheData)
                    val ostream = ObjectOutputStream(fstream)
                    ostream.writeObject(data!!)
                    ostream.flush()
                    fstream.flush()
                    ostream.close()
                    fstream.close()
                    isSuccess = true
                    msg = preventUpdate.msg
                } else
                    msg = "Yahhh... maaf gak bisa ngambil data :("

            } else {
                if (preventUpdate.enumResult == ResultConnection.IN_TESTING_MODE) {
                    data = FragmentTeamsImpl.getTeamData(leagueInURI)
                    msg = preventUpdate.msg
                    isSuccess = true
                } else if (preventUpdate.enumResult == ResultConnection.CACHE_IS_AVAIL) {
                    // read from --> cacheFilesTeams
                    val fstream = FileInputStream(cacheData)
                    val istream = ObjectInputStream(fstream)
                    data = istream.readObject() as TeamDataResponse?
                    istream.close()
                    fstream.close()
                    isSuccess = true
                }
                msg = preventUpdate.msg
            }


            view.hideLoading()
            if (isSuccess) {
                view.onFullyLoaded(data?.teams!!, msg!!)
            } else {
                view.onNotLoaded(msg!!)
            }

        }
    }

    private fun getAllTeamsListFromServer(leagueInURI: String): Deferred<Boolean> = GlobalScope.async {
            data = gson.fromJson(
                apiRepository.doRequest(GetTeamList.getAllTeamInLeague(leagueInURI)).await(),
                TeamDataResponse::class.java
            )
        data == null
    }
}