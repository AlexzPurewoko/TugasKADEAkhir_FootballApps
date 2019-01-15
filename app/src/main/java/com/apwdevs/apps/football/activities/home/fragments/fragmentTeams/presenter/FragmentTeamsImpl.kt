package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.presenter

import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamDataResponse

object FragmentTeamsImpl {

    private val listLeagues: MutableList<TeamListDataCls> = mutableListOf()

    init {
        listLeagues.clear()
        listLeagues.add(
            TeamListDataCls(
                buildToURI("English Premier League"),
                mutableListOf(
                    TeamData(
                        teamId = "133604",
                        teamName = "Arsenal",
                        teamBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png"
                    ),
                    TeamData(
                        teamId = "133610",
                        teamName = "Chelsea",
                        teamBadge = "https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png"
                    )
                )
            )
        )

        listLeagues.add(
            TeamListDataCls(
                buildToURI("Italian Serie A"),
                mutableListOf(
                    TeamData(
                        teamId = "134782",
                        teamName = "Atalanta",
                        teamBadge = "https://www.thesportsdb.com/images/media/team/badge/lrvxg71534873930.png"
                    ),
                    TeamData(
                        teamId = "133676",
                        teamName = "Juventus",
                        teamBadge = "https://www.thesportsdb.com/images/media/team/badge/dd6d6q1535186317.png"
                    )
                )
            )
        )
    }

    fun getTeamData(leagueName: String): TeamDataResponse? {
        for (league in listLeagues) {
            if (league.leagueId.equals(leagueName)) {
                return TeamDataResponse(league.data)
            }
        }
        return null
    }

    private fun buildToURI(name: String): String {
        val sbuf = StringBuffer()
        for (i in name) {
            if (i == ' ')
                sbuf.append("%20")
            else
                sbuf.append(i)
        }
        return sbuf.toString()
    }
}

data class TeamListDataCls(
    val leagueId: String,
    val data: MutableList<TeamData>
)