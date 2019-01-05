package com.apwdevs.apps.football.activities.detailTeams.ui

import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamsAbout

interface AboutTeamsModel {
    fun showLoading()
    fun hideLoading()
    fun onLoadCancelled(msg: String)
    fun onLoadFinished(team: TeamsAbout, players: List<TeamMemberShortData>, recyclerDataSets: List<DetailRecyclerData>)
}