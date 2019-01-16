package com.apwdevs.apps.football.activities.detailMatchs

import android.content.Intent
import android.support.design.widget.AppBarLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.ThreadTimes.LONG_TIME
import com.apwdevs.apps.football.ThreadTimes.MIDDLE_TIME
import com.apwdevs.apps.football.ThreadTimes.SHORT_TIME
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenterImpl
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.ParameterClass.ID_EVENT_MATCH_SELECTED
import com.apwdevs.apps.football.utility.ParameterClass.ID_SELECTED_LEAGUE_KEY
import com.apwdevs.apps.football.utility.ParameterClass.KEY_IS_APP_TESTING
import com.apwdevs.apps.football.utility.ParameterClass.LIST_LEAGUE_DATA
import com.apwdevs.apps.football.utility.ParameterClass.NAME_LEAGUE_KEY
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailMatchActivityTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<DetailMatchActivity> =
        ActivityTestRule(DetailMatchActivity::class.java, false, false)

    private val dataLeagues: MutableList<TeamLeagueData> = mutableListOf()
    private val idSelectedLeague = "4328"
    private val idSelectedMatch = "576675"
    private val leagueName = "English Premier League"

    @Before
    fun setUp() {
        val response = SplashPresenterImpl.getData()
        dataLeagues.addAll(response.leagues)
    }

    @Test
    fun testDetailMatchActivity() {
        val i = Intent()
        i.putExtra(KEY_IS_APP_TESTING, true)
        i.putExtra(LIST_LEAGUE_DATA, LeagueResponse(dataLeagues))
        i.putExtra(ID_EVENT_MATCH_SELECTED, idSelectedMatch)
        i.putExtra(ID_SELECTED_LEAGUE_KEY, idSelectedLeague)
        i.putExtra(NAME_LEAGUE_KEY, leagueName)
        mActivityRule.launchActivity(i)
        Thread.sleep(LONG_TIME)
        performToolbarAndRVTesting()
        performSwipeDownTesting()
        performAddToFavoritesTesting()
        Thread.sleep(LONG_TIME)
    }

    private fun performAddToFavoritesTesting() {
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

    private fun performSwipeDownTesting() {
        onView(withId(R.id.activity_detail_match_swiperefresh)).perform(
            withCustomConstraints(
                swipeDown(),
                isDisplayingAtLeast(50)
            )
        )
        Thread.sleep(SHORT_TIME)
        //check if snackbar is displayed on the screen
        onView(withText(ParameterClass.MSG_IN_TESTING_MODE)).check(matches(isDisplayed()))
    }

    private fun performToolbarAndRVTesting() {
        // smooth scroll and collapse the toolbar
        mActivityRule.activity.recyclerView.smoothScrollToPosition(mActivityRule.activity.recyclerView.adapter.itemCount - 1)
        onView(withId(R.id.activity_detail_match_appbar)).perform(collapseExpandAppBarLayout(false))
        Thread.sleep(MIDDLE_TIME)
        // perform checking, if just expanded, then return any errors
        var isExpanded = true
        try {
            onView(withText("vs")).check(matches(isDisplayed()))
        } catch (e: Throwable) {
            isExpanded = false
        }
        if (isExpanded)
            throw RuntimeException("The toolbar cannot be collapsed!")
        // smooth scroll and expand the toolbar
        mActivityRule.activity.recyclerView.smoothScrollToPosition(0)
        onView(withId(R.id.activity_detail_match_appbar)).perform(collapseExpandAppBarLayout(true))
        Thread.sleep(MIDDLE_TIME)
        // perform checking, if just collapsed, then return any errors
        try {
            onView(withText("vs")).check(matches(isDisplayed()))
            isExpanded = true
        } catch (e: Throwable) {
        }
        if (!isExpanded)
            throw RuntimeException("The toolbar cannot be expanded!")
    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

    fun collapseExpandAppBarLayout(expanded: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(AppBarLayout::class.java)
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