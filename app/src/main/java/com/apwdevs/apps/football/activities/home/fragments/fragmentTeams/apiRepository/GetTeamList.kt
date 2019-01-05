package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.apiRepository

import com.apwdevs.apps.football.BuildConfig

object GetTeamList {
    fun getAllTeamInLeague(leagueName: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=$leagueName"
}