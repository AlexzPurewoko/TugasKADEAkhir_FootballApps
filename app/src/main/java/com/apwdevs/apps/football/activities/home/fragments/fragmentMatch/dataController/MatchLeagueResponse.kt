package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController

import java.io.Serializable

data class MatchLeagueResponse(
    var events: List<MatchLeagueData>
) : Serializable