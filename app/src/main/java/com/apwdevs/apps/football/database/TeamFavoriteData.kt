package com.apwdevs.apps.football.database

data class TeamFavoriteData(
    val num: Int?,
    val teamId: String?,
    val teamName: String?,
    val logoPath: String?
) {
    companion object {
        const val TABLE_TEAM_FAVORITE = "TEAM_FAVORITE"
        const val ID_UNIQUE = "NUMB_ID"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_LOGO = "TEAM_LOGO"
    }
}