package com.apwdevs.apps.football.activities.detailTeams.dataController

import java.io.Serializable

data class DetailRecyclerData(
    val propertyName: String,
    var value: String?,
    val propertyType: PropertyRecyclerType
) : Serializable

data class DetailRecyclerCarry(
    val dataSets: List<DetailRecyclerData>
) : Serializable

enum class PropertyRecyclerType {
    PROPERTY_IMAGE,
    PROPERTY_INDEPENDENT,
    PROPERTY_ONLY_TEXT_VALUE
}

