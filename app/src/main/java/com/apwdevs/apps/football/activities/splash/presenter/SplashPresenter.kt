package com.apwdevs.apps.football.activities.splash.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.splash.apiRequest.GetLeagueSoccer
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.ui.SplashModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.CekKoneksi
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.*

class SplashPresenter(
    private val ctx: Context,
    private val view: SplashModel,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    private val fileDir = File(ctx.cacheDir, ParameterClass.LEAGUE_FILTER)
    fun getLeagueList() {
        view.onLoadingStarted()
        GlobalScope.launch(contextPool.main) {
            var msg: String? = null
            var finalData: List<TeamLeagueData>? = null
            if (!fileDir.exists()) {
                val isConnect = if (isTesting) true else CekKoneksi.isConnected(ctx).await()
                if (isConnect) {
                    val data = gson.fromJson(
                        apiRepository.doRequest(GetLeagueSoccer.getAllLeague()).await(),
                        LeagueResponse::class.java
                    )
                    if (data != null) {
                        val listTeamLeagueData = mutableListOf<TeamLeagueData>()
                        for (value in data.leagues) {
                            if (!value.sportType.equals(ParameterClass.LEAGUE_FILTER)) continue
                            listTeamLeagueData.add(value)
                        }
                        val fos = FileOutputStream(fileDir)
                        val oos = ObjectOutputStream(fos)
                        oos.writeObject(listTeamLeagueData.toList())
                        oos.flush()
                        fos.flush()
                        oos.close()
                        fos.close()
                        finalData = listTeamLeagueData.toList()
                    } else {
                        msg = "Cannot get the data from internet!, please make sure you connected the internet!"
                    }
                } else {
                    msg =
                            "Please activate your internet connection first before launching this app, Okay? \n\nERR_INET_MISSING\n:("
                }
            } else {
                if (if (isTesting) true else CekKoneksi.isConnected(ctx).await()) {
                    try {
                        val fis = FileInputStream(fileDir)
                        val ois = ObjectInputStream(fis)
                        finalData = ois.readObject() as List<TeamLeagueData>
                        ois.close()
                        fis.close()
                    } catch (e: IOException) {
                        fileDir.delete()
                        msg =
                                "$e. Please make sure you restart the application. If this problem is occur again, try reinstalling the app\n\nERR_IO_EXCEPTION\n:("
                    }
                } else msg =
                        "Please activate your internet connection first before launching this app, Okay? \n\nERR_INET_MISSING\n:("


                if (isTesting)
                    Thread.sleep(1500)
                else
                    delay(1500)
            }

            if (finalData != null)
                view.onLoadingSuccesfully(finalData)
            else
                view.onLoadFailed(msg ?: "Null Message")
        }

    }
}