package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.ui

import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse

interface FragmentNextMatchModel {
    fun onShowLoading()
    fun onHideLoading()
    fun onRequestFailed(msg: String)
    fun onDataSucceeded(leagues: MatchLeagueResponse, msg: String)
}