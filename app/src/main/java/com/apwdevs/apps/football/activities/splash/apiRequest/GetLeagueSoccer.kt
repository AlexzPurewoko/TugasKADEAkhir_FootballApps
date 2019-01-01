package com.apwdevs.apps.football.activities.splash.apiRequest

import com.apwdevs.apps.football.BuildConfig

object GetLeagueSoccer {
    fun getAllLeague(): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php"
    }
}