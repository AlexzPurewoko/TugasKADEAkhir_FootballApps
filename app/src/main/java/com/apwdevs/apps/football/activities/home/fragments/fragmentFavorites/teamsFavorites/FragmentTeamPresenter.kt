package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.teamsFavorites

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.apwdevs.apps.football.database.TeamFavoriteData
import com.apwdevs.apps.football.database.database
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FragmentTeamPresenter(
    private val ctx: Context,
    private val model: FragmentTeamModel,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getFromDatabase() {
        model.showLoading()
        GlobalScope.launch(contextPool.main) {
            ctx.database.use {
                val result = select(TeamFavoriteData.TABLE_TEAM_FAVORITE)
                val retrieve = result.parseList(classParser<TeamFavoriteData>())
                if (!retrieve.isEmpty()) {
                    // return with assigned data
                    // copy data
                    val returnedData = mutableListOf<TeamData>()
                    for (value in retrieve) {
                        returnedData.add(
                            TeamData(
                                value.teamId,
                                value.teamName,
                                value.logoPath
                            )
                        )
                    }
                    model.hideLoading()
                    model.onLoaded(returnedData, retrieve)

                } else {
                    model.hideLoading()
                    model.onLoaded(mutableListOf(), mutableListOf())
                }
            }
        }
    }
}