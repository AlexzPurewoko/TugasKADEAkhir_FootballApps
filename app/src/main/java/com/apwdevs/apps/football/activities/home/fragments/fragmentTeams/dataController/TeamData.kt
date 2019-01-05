package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController

import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)