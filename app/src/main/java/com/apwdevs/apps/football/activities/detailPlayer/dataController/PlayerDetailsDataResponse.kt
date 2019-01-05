package com.apwdevs.apps.football.activities.detailPlayer.dataController

import java.io.Serializable

data class PlayerDetailsDataResponse(
    val players: List<PlayersDetailData>
) : Serializable