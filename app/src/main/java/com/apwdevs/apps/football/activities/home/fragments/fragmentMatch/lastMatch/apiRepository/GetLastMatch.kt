package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.apiRepository

import com.apwdevs.apps.football.BuildConfig

object GetLastMatch {
    fun getMatch(leagueId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=$leagueId"
}