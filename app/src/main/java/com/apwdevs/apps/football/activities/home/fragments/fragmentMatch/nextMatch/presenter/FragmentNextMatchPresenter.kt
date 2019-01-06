package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.apiRepository.GetNextMatch
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.ui.FragmentNextMatchModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.CekKoneksi
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.*

class FragmentNextMatchPresenter(
    private val ctx: Context,
    private var view: FragmentNextMatchModel,
    private val gson: Gson,
    private val apiRepository: ApiRepository,
    private val enableCaching: Boolean = true,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {


    /*fun getMatch(leagueId: String) {
        view.onShowLoading()
        GlobalScope.launch(contextPool.main) {
            var msg: String? = null
            var data: MatchLeagueResponse? = null

            if (
                if (isTesting) true
                else CekKoneksi.isConnected(ctx).await()
            ) {
                data = gson.fromJson(
                    apiRepository.doRequest(GetNextMatch.getMatch(leagueId)).await(),
                    MatchLeagueResponse::class.java
                )
                if (data == null)
                    msg = "Failed to get data from server"
            } else {
                msg = "Your phone isn't connected into the internet. Make sure you check the connection"
            }

            if (msg == null && data != null) {
                view.onHideLoading()
                view.onDataSucceeded(data)
            } else {
                view.onHideLoading()
                view.onRequestFailed(msg!!)
            }
        }
    }*/
    var msg: String? = null
    var data: MatchLeagueResponse? = null

    fun getMatch(leagueId: String) {

        view.onShowLoading()
        GlobalScope.launch(contextPool.main) {
            if (enableCaching) {

                val cacheData = File(ctx.cacheDir, "last_match$leagueId")
                if (cacheData.exists()) {

                    // read from --> cacheFilesTeams
                    val fstream = FileInputStream(cacheData)
                    val istream = ObjectInputStream(fstream)
                    data = istream.readObject() as MatchLeagueResponse?
                    istream.close()
                    fstream.close()

                    if (data == null)
                        msg = "Cannot get data from the disk!"
                } else {
                    if (getMatchFromServer(leagueId).await()) {
                        // save into file --> cacheData
                        val fstream = FileOutputStream(cacheData)
                        val ostream = ObjectOutputStream(fstream)
                        ostream.writeObject(data!!)
                        ostream.flush()
                        fstream.flush()
                        ostream.close()
                        fstream.close()
                    }
                }

            } else
                getMatchFromServer(leagueId).await()

            view.onHideLoading()
            if (msg == null && data != null)
                view.onDataSucceeded(data!!)
            else
                view.onRequestFailed(msg!!)
        }
    }

    private fun getMatchFromServer(leagueId: String): Deferred<Boolean> = GlobalScope.async {
        if (
            if (isTesting) true
            else CekKoneksi.isConnected(ctx).await()
        ) {
            data = gson.fromJson(
                apiRepository.doRequest(GetNextMatch.getMatch(leagueId)).await(),
                MatchLeagueResponse::class.java
            )
            if (data == null)
                msg = "Failed to get data from server"
        } else {
            msg = "Your phone isn't connected into the internet. Make sure you check the connection"
        }

        msg != null
    }


}