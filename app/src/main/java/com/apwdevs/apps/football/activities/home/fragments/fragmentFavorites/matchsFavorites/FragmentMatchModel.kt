package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.matchsFavorites

import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.database.MatchFavoriteData

interface FragmentMatchModel {
    fun showLoading()
    fun hideLoading()
    fun onLoaded(listMatch: MutableList<MatchLeagueData>, listFavorite: List<MatchFavoriteData>)
}