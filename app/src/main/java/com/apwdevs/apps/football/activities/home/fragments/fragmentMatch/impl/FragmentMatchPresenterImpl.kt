package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.impl

import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse

object FragmentMatchPresenterImpl {
    private val leagueList: MutableList<FragmentMatchDataCls> = mutableListOf()

    init {
        leagueList.clear()
        leagueList.add(
            FragmentMatchDataCls(
                "4328",
                mutableListOf(
                    MatchLeagueData(
                        eventId = "576675",
                        homeTeamName = "Man City",
                        awayTeamName = "Liverpool",
                        homeTeamScore = "2",
                        awayTeamScore = "1",
                        date = "03/01/19",
                        time = "20:00:00+00:00"
                    ),
                    MatchLeagueData(
                        eventId = "576679",
                        homeTeamName = "West Ham",
                        awayTeamName = "Brighton",
                        homeTeamScore = "2",
                        awayTeamScore = "2",
                        date = "02/01/19",
                        time = "19:45:00+00:00"
                    ),
                    //next match
                    MatchLeagueData(
                        eventId = "576681",
                        homeTeamName = "Leicester",
                        awayTeamName = "Southampton",
                        homeTeamScore = null,
                        awayTeamScore = null,
                        date = "12/01/19",
                        time = "15:00:00+00:00"
                    ),
                    MatchLeagueData(
                        eventId = "576684",
                        homeTeamName = "Burnley",
                        awayTeamName = "Fulham",
                        homeTeamScore = null,
                        awayTeamScore = null,
                        date = "12/01/19",
                        time = "15:00:00+00:00"
                    )
                )
            )
        )
        leagueList.add(
            FragmentMatchDataCls(
                "4332",
                mutableListOf(
                    MatchLeagueData(
                        eventId = "582235",
                        homeTeamName = "Empoli",
                        awayTeamName = "Inter",
                        homeTeamScore = "0",
                        awayTeamScore = "1",
                        date = "29/12/18",
                        time = "14:00:00+00:00"
                    ),
                    MatchLeagueData(
                        eventId = "582234",
                        homeTeamName = "Parma Calcio 1913",
                        awayTeamName = "Roma",
                        homeTeamScore = "0",
                        awayTeamScore = "2",
                        date = "29/12/18",
                        time = "14:00:00+00:00"
                    ),
                    //next match
                    MatchLeagueData(
                        eventId = "582239",
                        homeTeamName = "Roma",
                        awayTeamName = "Torino",
                        homeTeamScore = null,
                        awayTeamScore = null,
                        date = "19/01/19",
                        time = "14:00:00+00:00"
                    ),
                    MatchLeagueData(
                        eventId = "582243",
                        homeTeamName = "Udinese",
                        awayTeamName = "Parma Calcio 1913",
                        homeTeamScore = null,
                        awayTeamScore = null,
                        date = "19/01/19",
                        time = "17:00:00+00:00"
                    )
                )
            )
        )
    }

    fun getDataNextMatch(leagueId: String): MatchLeagueResponse? {
        var league: FragmentMatchDataCls? = null
        for (l in leagueList) {
            if (l.leagueId == leagueId) {
                league = l
                break
            }
        }

        if (league == null) return null
        val matchData = mutableListOf<MatchLeagueData>()
        for (match in league.listMatch) {
            if (match.homeTeamScore.isNullOrBlank() || match.awayTeamName.isNullOrBlank())
                matchData.add(match)
        }
        return MatchLeagueResponse(matchData)
    }

    fun getDataLastMatch(leagueId: String): MatchLeagueResponse? {
        var league: FragmentMatchDataCls? = null
        for (l in leagueList) {
            if (l.leagueId == leagueId) {
                league = l
                break
            }
        }

        if (league == null) return null
        val matchData = mutableListOf<MatchLeagueData>()
        for (match in league.listMatch) {
            if (match.homeTeamScore.isNullOrBlank() || match.awayTeamName.isNullOrBlank()) continue
            matchData.add(match)
        }
        return MatchLeagueResponse(matchData)
    }

}

data class FragmentMatchDataCls(
    val leagueId: String,
    val listMatch: MutableList<MatchLeagueData>
)