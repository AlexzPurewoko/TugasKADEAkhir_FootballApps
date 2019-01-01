package com.apwdevs.apps.football.activities.splash.dataController

import java.io.Serializable

data class LeagueResponse(
    val leagues: List<TeamLeagueData>
) : Serializable