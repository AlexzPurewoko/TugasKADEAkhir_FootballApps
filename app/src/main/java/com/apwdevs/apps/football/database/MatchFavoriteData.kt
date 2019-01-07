package com.apwdevs.apps.football.database

data class MatchFavoriteData(
    val num: Int?,
    val idLeague: String?,
    val idEvent: String?,
    val leagueName: String?,
    val dateEvent: String?,
    val timeEvent: String?,
    val homeTeam: String?,
    val homeScore: Int?,
    val awayTeam: String?,
    val awayScore: Int?
) {
    companion object {
        const val TABLE_MATCH_FAVORITE = "MATCH_FAVORITE"
        const val ID_LEAGUE = "ID_LEAGUE"
        const val ID_EVENT = "ID_EVENT"
        const val LEAGUE_NAME = "LEAGUE_NAME"
        const val DATE_EVENT = "DATE_EVENT"
        const val TIME_EVENT = "TIME_EVENT"
        const val HOME_TEAM = "HOME_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val ID_UNIQUE = "NUMB_ID"

    }
}