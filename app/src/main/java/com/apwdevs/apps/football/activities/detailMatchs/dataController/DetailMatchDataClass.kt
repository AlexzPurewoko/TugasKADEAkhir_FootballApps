package com.apwdevs.apps.football.activities.detailMatchs.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailMatchDataClass(
    // statistics
    @SerializedName("dateEvent")
    var dateEvent: String?,
    @SerializedName("strTime")
    var timeEvent: String?,
    //// id team
    @SerializedName("idHomeTeam")
    var idHomeTeam: String?,
    @SerializedName("idAwayTeam")
    var idAwayTeam: String?,
    //// goals
    @SerializedName("intHomeScore")
    var intHomeScore: String?,
    @SerializedName("intAwayScore")
    var intAwayScore: String?,
    //// shots
    @SerializedName("intHomeShots")
    var intHomeShots: String?,
    @SerializedName("intAwayShots")
    var intAwayShots: String?,
    //// team_name
    @SerializedName("strHomeTeam")
    var strHomeTeam: String?,
    @SerializedName("strAwayTeam")
    var strAwayTeam: String?,
    //// Goal Details
    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String?,
    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String?,
    //// Red Cards
    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String?,
    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String?,
    //// Yellow Cards
    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String?,
    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String?,

    /////////////
    // lineups
    //// Goalkeeper
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String?,
    //// Defense
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String?,
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String?,
    //// Mildfield
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String?,
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String?,
    //// Forward
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String?,
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String?,
    //// Subtitues
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String?,
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String?
) : Serializable