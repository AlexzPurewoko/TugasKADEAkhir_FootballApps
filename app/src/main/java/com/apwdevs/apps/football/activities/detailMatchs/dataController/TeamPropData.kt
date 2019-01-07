package com.apwdevs.apps.football.activities.detailMatchs.dataController

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamPropData(
    @SerializedName("idTeam")
    val idTeam: String?,
    @SerializedName("strTeam")
    val strTeam: String?,
    @SerializedName("strTeamBadge")
    val strTeamBadge: String?
) : Serializable

data class TeamPropDataResponse(
    val teams: List<TeamPropData>
) : Serializable