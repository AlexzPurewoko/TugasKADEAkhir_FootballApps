package com.apwdevs.apps.football.activities.detailMatchs.apiRequest

import com.apwdevs.apps.football.BuildConfig

object GetMatchDetailDataFromApi {
    fun getDetailMatchURL(eventId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$eventId"

    fun getImageClubURL(clubId: String): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$clubId"
}