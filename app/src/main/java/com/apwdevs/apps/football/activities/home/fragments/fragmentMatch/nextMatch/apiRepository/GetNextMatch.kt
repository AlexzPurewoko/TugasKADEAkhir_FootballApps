package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.apiRepository

import com.apwdevs.apps.football.BuildConfig

object GetNextMatch {
    fun getMatch(leagueId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=$leagueId"
}