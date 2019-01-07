package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.teamsFavorites

import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.apwdevs.apps.football.database.TeamFavoriteData

interface FragmentTeamModel {
    fun showLoading()
    fun hideLoading()
    fun onLoaded(listMatch: MutableList<TeamData>, listFavorite: List<TeamFavoriteData>)
}