package com.apwdevs.apps.football.activities.detailTeams.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailTeams.api.GetsAboutTeam
import com.apwdevs.apps.football.activities.detailTeams.dataController.*
import com.apwdevs.apps.football.activities.detailTeams.ui.AboutTeamsModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.AvailableDataUpdates
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import com.apwdevs.apps.football.utility.ResultConnection
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.*

class AboutTeamsPresenter(
    private val ctx: Context,
    private val view: AboutTeamsModel,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val isTesting: Boolean = false,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    private var msg: String? = null
    private var teams: TeamsAbout? = null
    private var listPlayers: List<TeamMemberShortData>? = null
    private val recyclerDataSets: MutableList<DetailRecyclerData> = mutableListOf()
    private var countUserRefresh: Int = 0
    fun getDataBehaviour(teamId: String) {
        view.showLoading()
        GlobalScope.launch(contextPool.main) {
            val cacheFilesTeams = File(ctx.cacheDir, "aboutteams_$teamId")
            val cacheFilesMemberTeam = File(ctx.cacheDir, "member_team_$teamId")
            val preventUpdate = AvailableDataUpdates.isAvailable(
                mutableListOf(cacheFilesTeams, cacheFilesMemberTeam),
                isTesting,
                countUserRefresh++
            ).await()
            var isSuccess = false
            recyclerDataSets.clear()
            if (preventUpdate.preventToUpdate) {
                    val data = getDataBehaviourFromInet(teamId).await()
                    if (!data) {
                        // save into file --> cacheFilesTeams
                        var fstream = FileOutputStream(cacheFilesTeams)
                        var ostream = ObjectOutputStream(fstream)
                        ostream.writeObject(teams!!)
                        ostream.flush()
                        fstream.flush()
                        ostream.close()
                        fstream.close()

                        // save into file --> cacheFilesMemberTeam
                        fstream = FileOutputStream(cacheFilesMemberTeam)
                        ostream = ObjectOutputStream(fstream)
                        ostream.writeObject(listPlayers!!)
                        ostream.flush()
                        fstream.flush()
                        ostream.close()
                        fstream.close()
                        isSuccess = true
                        getRecyclerDataSets().await()
                        msg = preventUpdate.msg
                    } else msg = "Yahhh... Error saat ngambil data dari server... maaf yaa"
            } else {
                if (preventUpdate.enumResult == ResultConnection.CACHE_IS_AVAIL) {
                    // read from --> cacheFilesTeams
                    var fstream = FileInputStream(cacheFilesTeams)
                    var istream = ObjectInputStream(fstream)
                    teams = istream.readObject() as TeamsAbout?
                    istream.close()
                    fstream.close()

                    // read from --> cacheFilesMemberTeam
                    fstream = FileInputStream(cacheFilesMemberTeam)
                    istream = ObjectInputStream(fstream)
                    listPlayers = istream.readObject() as List<TeamMemberShortData>?
                    getRecyclerDataSets().await()
                    istream.close()
                    fstream.close()
                    isSuccess = true
                }
                msg = preventUpdate.msg
            }
            if (isTesting) Thread.sleep(1000)
            else delay(1000)

            view.hideLoading()
            if (isSuccess)
                view.onLoadFinished(teams!!, listPlayers ?: mutableListOf(), recyclerDataSets, msg!!)
            else
                view.onLoadCancelled(msg!!)
        }
    }

    private fun getDataBehaviourFromInet(teamId: String): Deferred<Boolean> = GlobalScope.async {
            // get data teams
            val aboutTeams = getAboutTeams(teamId)
            if (aboutTeams.await()) {
                val listPlayers = getListPlayers(teamId)
                if (!listPlayers.await()) {
                    msg = "Can't retrieve player data on Team id $teamId"
                    true
                } else false
            } else {
                msg = "Can't get data from TheSportsDB!, maybe failure on internet connection"
                true
            }
    }

    private fun getAboutTeams(teamId: String): Deferred<Boolean> = GlobalScope.async {
        val data = gson.fromJson(
            apiRepository.doRequest(GetsAboutTeam.getTeamDetail(teamId)).await(),
            TeamsAboutResponse::class.java
        )
        if (data == null)
            false
        else {
            teams = data.teams[0]
            true
        }
    }

    private fun getListPlayers(teamId: String): Deferred<Boolean> = GlobalScope.async {
        val data = gson.fromJson(
            apiRepository.doRequest(GetsAboutTeam.getAllPlayersOnTeam(teamId)).await(),
            TeamMemberShortDataResponse::class.java
        )
        if (data == null)
            false
        else {
            listPlayers = data.player
            true
        }
    }

    private fun getRecyclerDataSets(): Deferred<Boolean> = GlobalScope.async {
        recyclerDataSets.add(DetailRecyclerData("Teams", null, PropertyRecyclerType.PROPERTY_INDEPENDENT))
        recyclerDataSets.add(
            DetailRecyclerData(
                "Team ID",
                teams?.teamId,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Formed On",
                teams?.formedOnYear,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Team Manager Name",
                teams?.managerTeamName,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Gender",
                teams?.clubGender,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Country",
                teams?.clubCountry,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(DetailRecyclerData("Teams Badge", teams?.teamBadge, PropertyRecyclerType.PROPERTY_IMAGE))
        recyclerDataSets.add(
            DetailRecyclerData(
                "Teams Banner",
                teams?.teamBanner,
                PropertyRecyclerType.PROPERTY_IMAGE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Teams Jersey",
                teams?.teamJersey,
                PropertyRecyclerType.PROPERTY_IMAGE
            )
        )
        recyclerDataSets.add(DetailRecyclerData("Teams Logo", teams?.teamLogo, PropertyRecyclerType.PROPERTY_IMAGE))
        recyclerDataSets.add(
            DetailRecyclerData(
                "Teams Fan Art",
                teams?.teamFanArt,
                PropertyRecyclerType.PROPERTY_IMAGE
            )
        )


        recyclerDataSets.add(DetailRecyclerData("Stadium", null, PropertyRecyclerType.PROPERTY_INDEPENDENT))
        recyclerDataSets.add(
            DetailRecyclerData(
                "Stadium Name",
                teams?.stadiumName,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Stadium Images",
                teams?.stadiumImage,
                PropertyRecyclerType.PROPERTY_IMAGE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Stadium Description",
                teams?.stadiumDesc,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Stadium Location",
                teams?.stadiumLocation,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Stadium Capacity",
                teams?.stadiumCapacity,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )


        recyclerDataSets.add(DetailRecyclerData("Social Media", null, PropertyRecyclerType.PROPERTY_INDEPENDENT))
        recyclerDataSets.add(
            DetailRecyclerData(
                "Website",
                teams?.urlWebsite,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Facebook",
                teams?.facebookUrl,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Twitter",
                teams?.twitterUrl,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.add(
            DetailRecyclerData(
                "Instagram",
                teams?.instagramProfiles,
                PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE
            )
        )
        recyclerDataSets.size > 1
    }
}