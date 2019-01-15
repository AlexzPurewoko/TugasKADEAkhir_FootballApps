package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.impl.FragmentMatchPresenterImpl
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.apiRepository.GetLastMatch
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui.FragmentLastMatchModel
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

class FragmentLastMatchPresenter(
    private val ctx: Context,
    private var view: FragmentLastMatchModel,
    private val gson: Gson,
    private val apiRepository: ApiRepository,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    var msg: String? = null
    var data: MatchLeagueResponse? = null
    var countUserRefresh = 0

    fun getMatch(leagueId: String) {

        view.onShowLoading()
        GlobalScope.launch(contextPool.main) {
            val cacheData = File(ctx.cacheDir, "last_match$leagueId")
            val preventUpdate =
                AvailableDataUpdates.isAvailable(mutableListOf(cacheData), isTesting, countUserRefresh++, contextPool)
                    .await()
            var isSuccess = false
            if (preventUpdate.preventToUpdate) {
                if (!getMatchFromServer(leagueId).await()) {
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
                    msg = "Yahhh... Error saat ngambil data dari server... maaf yaa"


            } else {
                if (preventUpdate.enumResult == ResultConnection.IN_TESTING_MODE) {
                    data = FragmentMatchPresenterImpl.getDataLastMatch(leagueId)
                    msg = preventUpdate.msg
                    isSuccess = true
                } else if (preventUpdate.enumResult == ResultConnection.CACHE_IS_AVAIL) {
                    // read from --> cacheFilesTeams
                    val fstream = FileInputStream(cacheData)
                    val istream = ObjectInputStream(fstream)
                    data = istream.readObject() as MatchLeagueResponse?
                    istream.close()
                    fstream.close()
                    isSuccess = true
                }
                msg = preventUpdate.msg
            }

            view.onHideLoading()
            if (isSuccess)
                view.onDataSucceeded(data!!, msg!!)
            else
                view.onRequestFailed(msg!!)
        }
    }

    private fun getMatchFromServer(leagueId: String): Deferred<Boolean> = GlobalScope.async {
            data = gson.fromJson(
                apiRepository.doRequest(GetLastMatch.getMatch(leagueId)).await(),
                MatchLeagueResponse::class.java
            )
        data == null
    }
}