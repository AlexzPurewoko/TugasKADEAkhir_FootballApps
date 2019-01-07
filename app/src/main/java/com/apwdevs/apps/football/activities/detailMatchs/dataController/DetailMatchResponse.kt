package com.apwdevs.apps.football.activities.detailMatchs.dataController

import java.io.Serializable

data class DetailMatchResponse(
    val events: List<DetailMatchDataClass>
) : Serializable