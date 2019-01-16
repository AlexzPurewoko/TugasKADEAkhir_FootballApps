package com.apwdevs.apps.football.activities.detailPlayer

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.apwdevs.apps.football.ThreadTimes
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenterImpl
import com.apwdevs.apps.football.utility.ParameterClass
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlayerDetailsTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<PlayerDetails> = ActivityTestRule(PlayerDetails::class.java, false, false)

    private val dataLeagues: MutableList<TeamLeagueData> = mutableListOf()
    private val idSelectedTeam = "133604"
    private val idSelectedPlayer = "34145411"
    private val leagueName = "English Premier League"

    @Before
    fun setUp() {
        val response = SplashPresenterImpl.getData()
        dataLeagues.addAll(response.leagues)
    }

    @Test
    fun testPlayerDetails() {
        val i = Intent()
        i.putExtra(ParameterClass.KEY_IS_APP_TESTING, true)
        i.putExtra(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(dataLeagues))
        i.putExtra(ParameterClass.ID_SELECTED_TEAMS, idSelectedTeam)
        i.putExtra(ParameterClass.ID_SELECTED_PLAYERS, idSelectedPlayer)
        i.putExtra(ParameterClass.NAME_LEAGUE_KEY, leagueName)
        mActivityRule.launchActivity(i)
        Thread.sleep(ThreadTimes.LONG_TIME)

    }
}