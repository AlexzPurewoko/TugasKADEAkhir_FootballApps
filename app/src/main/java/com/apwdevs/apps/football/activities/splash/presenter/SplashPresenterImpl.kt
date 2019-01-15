package com.apwdevs.apps.football.activities.splash.presenter

import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData

object SplashPresenterImpl {
    fun getData(): LeagueResponse {
        val dataSets = mutableListOf<TeamLeagueData>()
        dataSets.clear()
        dataSets.add(
            TeamLeagueData(
                leagueId = "4328",
                leagueName = "English Premier League",
                sportType = "Soccer"
            )
        )
        dataSets.add(
            TeamLeagueData(
                leagueId = "4332",
                leagueName = "Italian Serie A",
                sportType = "Soccer"
            )
        )
        return LeagueResponse(dataSets)
    }
}