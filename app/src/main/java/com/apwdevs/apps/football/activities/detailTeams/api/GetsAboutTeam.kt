package com.apwdevs.apps.football.activities.detailTeams.api

import com.apwdevs.apps.football.BuildConfig

object GetsAboutTeam {
    fun getTeamDetail(teamId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$teamId"

    fun getAllPlayersOnTeam(teamId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=$teamId"

}