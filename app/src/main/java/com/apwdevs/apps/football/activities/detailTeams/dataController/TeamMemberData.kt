package com.apwdevs.apps.football.activities.detailTeams.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamMemberShortData(
    @SerializedName("idPlayer")
    var playerId: String?,
    @SerializedName("strPlayer")
    var playerName: String?,
    @SerializedName("strPosition")
    var playerPosition: String?,
    @SerializedName("strCutout")
    var playerPhotosUrl: String?
) : Serializable

data class TeamMemberShortDataResponse(
    val player: List<TeamMemberShortData>
) : Serializable