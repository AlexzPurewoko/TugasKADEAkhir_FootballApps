package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui

import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse

interface FragmentLastMatchModel {
    fun onShowLoading()
    fun onHideLoading()
    fun onRequestFailed(msg: String)
    fun onDataSucceeded(leagues: MatchLeagueResponse)
}