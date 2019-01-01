package com.apwdevs.apps.football.activities.splash.ui

import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData

interface SplashModel {
    fun onLoadingStarted()
    fun onLoadFailed(msg: String)
    fun onLoadingSuccesfully(leagues: List<TeamLeagueData>)
}