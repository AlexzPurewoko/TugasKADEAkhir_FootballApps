package com.apwdevs.apps.football.activities.detailPlayer.apiResponse

import com.apwdevs.apps.football.BuildConfig

object GetPlayerDetails {
    fun getDetails(playerId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupplayer.php?id=$playerId"
}