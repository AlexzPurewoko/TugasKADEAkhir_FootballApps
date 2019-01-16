package com.apwdevs.apps.football.activities.detailTeams

import android.content.Intent
import android.support.design.widget.AppBarLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.view.View
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.ThreadTimes
import com.apwdevs.apps.football.ThreadTimes.LONG_TIME
import com.apwdevs.apps.football.ThreadTimes.MIDDLE_TIME
import com.apwdevs.apps.football.ThreadTimes.SHORT_TIME
import com.apwdevs.apps.football.activities.detailTeams.fragments.DetailTeamsFragment
import com.apwdevs.apps.football.activities.detailTeams.presenter.AboutTeamsPresenterImpl
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenterImpl
import com.apwdevs.apps.football.utility.ParameterClass
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutTeamsTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<AboutTeams> = ActivityTestRule(AboutTeams::class.java, false, false)

    private val dataLeagues: MutableList<TeamLeagueData> = mutableListOf()
    private val idSelectedTeam = "133604"
    private val teamSelectedName = AboutTeamsPresenterImpl.getAboutTeams(idSelectedTeam)
    private val leagueName = "English Premier League"

    @Before
    fun setUp() {
        val response = SplashPresenterImpl.getData()
        dataLeagues.addAll(response.leagues)
    }

    @Test
    fun testAboutTeams() {
        val i = Intent()
        i.putExtra(ParameterClass.KEY_IS_APP_TESTING, true)
        i.putExtra(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(dataLeagues))
        i.putExtra(ParameterClass.ID_SELECTED_TEAMS, idSelectedTeam)
        i.putExtra(ParameterClass.NAME_LEAGUE_KEY, leagueName)
        mActivityRule.launchActivity(i)
        Thread.sleep(MIDDLE_TIME)
        performFirstSwipe()
        performClickOnTab()
        performSwipeUpAndTestToolbar()
        performClickOnFirstPlayerAndBack()
        performCheckAddToFavorite()
        Thread.sleep(ThreadTimes.LONG_TIME)
    }

    private fun performCheckAddToFavorite() {
        onView(withId(R.id.action_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        Thread.sleep(SHORT_TIME)
        // check the message
        onView(withText(ParameterClass.STRING_ADD_INTO_DATABASE)).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
        onView(withId(R.id.action_favorite)).perform(click())
        Thread.sleep(SHORT_TIME)
        // check the message
        onView(withText(ParameterClass.STRING_REMOVE_FROM_DATABASE)).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
    }

    private fun performClickOnFirstPlayerAndBack() {
        onView(withId(R.id.fragment_listmember_recyclerView)).check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(LONG_TIME)
        onView(isRoot()).perform(pressBack())
        Thread.sleep(MIDDLE_TIME)
    }

    private fun performSwipeUpAndTestToolbar() {
        onView(withText(R.string.aboutteams_tab_title_details)).perform(click())
        Thread.sleep(SHORT_TIME)
        val fragment = mActivityRule.activity.supportFragmentManager.fragments[1] as DetailTeamsFragment
        fragment.recyclerView.smoothScrollToPosition(fragment.recyclerView.adapter.itemCount - 1)

        onView(withId(R.id.activity_aboutteams_appbar)).perform(setAppBar(false))
        Thread.sleep(MIDDLE_TIME)
        // perform checking, if just expanded, then return any errors
        var isExpanded = true
        try {
            onView(withText(teamSelectedName?.teams!![0].teamName)).check(matches(isDisplayed()))
        } catch (e: Throwable) {
            isExpanded = false
        }
        if (isExpanded)
            throw RuntimeException("The toolbar cannot be collapsed!")

        // move into tab players
        onView(withText(R.string.aboutteams_tab_title_player)).perform(click())
        onView(withText(teamSelectedName?.teams!![0].teamName)).check(matches(isDisplayed()))
        Thread.sleep(MIDDLE_TIME)
    }

    private fun performClickOnTab() {
        val text = arrayListOf(
            R.string.aboutteams_tab_title_details,
            R.string.aboutteams_tab_title_player,
            R.string.aboutteams_tab_title_overview
        )
        for (selectedText in text) {
            onView(withText(selectedText)).check(matches(isDisplayed()))
            onView(withText(selectedText)).perform(click())
            Thread.sleep(SHORT_TIME)
        }
    }

    private fun performFirstSwipe() {
        onView(isRoot()).perform(swipeLeft(), swipeLeft(), swipeLeft())
        Thread.sleep(MIDDLE_TIME)
        onView(isRoot()).perform(swipeRight(), swipeRight(), swipeRight())
        Thread.sleep(MIDDLE_TIME)
    }

    fun setAppBar(expanded: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(AppBarLayout::class.java)
            }

            override fun getDescription(): String {
                return "Collapse App Bar Layout"
            }

            override fun perform(uiController: UiController, view: View) {
                val appBarLayout = view as AppBarLayout
                appBarLayout.setExpanded(expanded)
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}