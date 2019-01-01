package com.apwdevs.apps.football.activities.splash.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamLeagueData(
    @SerializedName("idLeague")
    var leagueId: String?,
    @SerializedName("strLeague")
    var leagueName: String?,
    @SerializedName("strSport")
    var sportType: String?
) : Serializable