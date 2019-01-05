package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui

import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData

interface FragmentTeamsModel {
    fun showLoading()
    fun hideLoading()
    fun onNotLoaded(msg: String)
    fun onFullyLoaded(teams: List<TeamData>)
}