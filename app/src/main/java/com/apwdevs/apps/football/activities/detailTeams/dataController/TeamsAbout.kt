package com.apwdevs.apps.football.activities.detailTeams.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamsAbout(
    @SerializedName("idTeam")
    var teamId: String?,
    @SerializedName("strTeam")
    var teamName: String?,
    @SerializedName("intFormedYear")
    var formedOnYear: String?,
    @SerializedName("strManager")
    var managerTeamName: String?,
    @SerializedName("strStadium")
    var stadiumName: String?,
    @SerializedName("strStadiumThumb")
    var stadiumImage: String?,
    @SerializedName("strStadiumDescription")
    var stadiumDesc: String?,
    @SerializedName("strStadiumLocation")
    var stadiumLocation: String?,
    @SerializedName("intStadiumCapacity")
    var stadiumCapacity: String?,

    @SerializedName("strWebsite")
    var urlWebsite: String?,
    @SerializedName("strFacebook")
    var facebookUrl: String?,
    @SerializedName("strTwitter")
    var twitterUrl: String?,
    @SerializedName("strInstagram")
    var instagramProfiles: String?,
    @SerializedName("strDescriptionEN")
    var clubDescription: String?,

    @SerializedName("strGender")
    var clubGender: String?,
    @SerializedName("strCountry")
    var clubCountry: String?,
    @SerializedName("strTeamBadge")
    var teamBadge: String?,
    @SerializedName("strTeamJersey")
    var teamJersey: String?,
    @SerializedName("strTeamLogo")
    var teamLogo: String?,
    @SerializedName("strTeamFanart1")
    var teamFanArt: String?,
    @SerializedName("strTeamBanner")
    var teamBanner: String?
) : Serializable

data class TeamsAboutResponse(
    val teams: List<TeamsAbout>
) : Serializable
