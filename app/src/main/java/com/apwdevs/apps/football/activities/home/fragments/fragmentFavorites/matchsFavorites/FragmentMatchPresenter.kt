package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.matchsFavorites

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.database.MatchFavoriteData
import com.apwdevs.apps.football.database.database
import com.apwdevs.apps.football.utility.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FragmentMatchPresenter(
    private val ctx: Context,
    private val model: FragmentMatchModel,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getFromDatabase() {
        model.showLoading()
        GlobalScope.launch(contextPool.main) {
            ctx.database.use {
                val result = select(MatchFavoriteData.TABLE_MATCH_FAVORITE)
                val retrieve = result.parseList(classParser<MatchFavoriteData>())
                if (!retrieve.isEmpty()) {
                    // return with assigned data
                    // copy data
                    val returnedData = mutableListOf<MatchLeagueData>()
                    for (value in retrieve) {
                        returnedData.add(
                            MatchLeagueData(
                                value.idEvent,
                                value.homeTeam,
                                value.awayTeam,
                                (value.homeScore ?: "").toString(),
                                (value.awayScore ?: "").toString(),
                                value.dateEvent,
                                value.timeEvent
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