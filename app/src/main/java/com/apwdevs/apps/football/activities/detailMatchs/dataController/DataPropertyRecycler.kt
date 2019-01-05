package com.apwdevs.apps.football.activities.detailMatchs.dataController

import java.io.Serializable

data class DataPropertyRecycler(
    val isProperty: Boolean,
    val name: String,
    val homeValue: String?,
    val awayValue: String?
) : Serializable