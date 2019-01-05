package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchLeagueData(
    @SerializedName("idEvent")
    var eventId: String?,
    @SerializedName("strHomeTeam")
    var homeTeamName: String?,
    @SerializedName("strAwayTeam")
    var awayTeamName: String?,
    @SerializedName("intHomeScore")
    var homeTeamScore: String?,
    @SerializedName("intAwayScore")
    var awayTeamScore: String?,
    @SerializedName("strDate")
    var date: String?,
    @SerializedName("strTime")
    var time: String?
) : Serializable