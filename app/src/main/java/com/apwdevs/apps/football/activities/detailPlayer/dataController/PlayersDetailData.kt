package com.apwdevs.apps.football.activities.detailPlayer.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayersDetailData(
    @SerializedName("idPlayer")
    var playerId: String?,
    @SerializedName("strPlayer")
    var playerName: String?,
    @SerializedName("strNationality")
    var nationality: String?,
    @SerializedName("strTeam")
    var playerTeam: String?,
    @SerializedName("dateBorn")
    var playerBorn: String?,
    @SerializedName("strBirthLocation")
    var birthLocation: String?,
    @SerializedName("strDescriptionEN")
    var playerDescription: String?,
    @SerializedName("strGender")
    var playerGender: String?,
    @SerializedName("strPosition")
    var playerPosition: String?,
    @SerializedName("strThumb")
    var playerPhotos: String?,
    @SerializedName("strHeight")
    var playerHeight: String?,
    @SerializedName("strWeight")
    var playerWeight: String?,


    @SerializedName("strFacebook")
    var facebook: String?,
    @SerializedName("strWebsite")
    var website: String?,
    @SerializedName("strTwitter")
    var twitter: String?,
    @SerializedName("strInstagram")
    var instagram: String?,
    @SerializedName("strYoutube")
    var youtube: String?,

    @SerializedName("strFanart1")
    var strFanart1: String?,
    @SerializedName("strFanart2")
    var strFanart2: String?,
    @SerializedName("strFanart3")
    var strFanart3: String?,
    @SerializedName("strFanart4")
    var strFanart4: String?

) : Serializable