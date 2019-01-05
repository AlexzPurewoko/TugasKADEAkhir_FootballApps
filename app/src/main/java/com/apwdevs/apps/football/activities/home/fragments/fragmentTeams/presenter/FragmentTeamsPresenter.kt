package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.apiRepository.GetTeamList
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamDataResponse
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.FragmentTeamsModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.CekKoneksi
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentTeamsPresenter(
    private val ctx: Context,
    private val view: FragmentTeamsModel,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {

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

        GlobalScope.launch(contextProvider.main) {
            var msg: String? = null
            var data: TeamDataResponse? = null
            val leagueInURI: String = buildToURI(leagueName)
            if (
                if (isTesting) true
                else CekKoneksi.isConnected(ctx).await()
            ) {
                data = gson.fromJson(
                    apiRepository.doRequest(GetTeamList.getAllTeamInLeague(leagueInURI)).await(),
                    TeamDataResponse::class.java
                )
                if (data == null)
                    msg = "Failed to get data from server"
            } else {
                msg = "Your phone isn't connected into the internet. Make sure you check the connection"
            }

            if (msg == null && data != null) {
                view.hideLoading()
                view.onFullyLoaded(data.teams)
            } else {
                view.hideLoading()
                view.onNotLoaded(msg!!)
            }
        }
    }
}