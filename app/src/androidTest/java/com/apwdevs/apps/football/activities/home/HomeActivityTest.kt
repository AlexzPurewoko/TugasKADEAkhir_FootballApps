package com.apwdevs.apps.football.activities.home

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.ThreadTimes
import com.apwdevs.apps.football.ThreadTimes.LONG_TIME
import com.apwdevs.apps.football.ThreadTimes.MIDDLE_TIME
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenterImpl
import com.apwdevs.apps.football.utility.ParameterClass
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java, false, false)
    private val dataResponse: MutableList<TeamLeagueData> = mutableListOf()
    @Before
    fun setUp() {
        val response = SplashPresenterImpl.getData()
        dataResponse.addAll(response.leagues)
    }

    @Test
    fun swipeTest() {

        val i = Intent()
        i.putExtra(ParameterClass.KEY_IS_APP_TESTING, true)
        i.putExtra(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(dataResponse))
        mActivityRule.launchActivity(i)
        Thread.sleep(ThreadTimes.MIDDLE_TIME)
        // try to swipe left
        onView(isRoot()).perform(
            swipeLeft(),
            swipeLeft(),
            swipeLeft(),
            swipeLeft(),
            swipeLeft(),
            swipeLeft(),
            swipeLeft()
        )
        // try to swipe right
        onView(isRoot()).perform(
            swipeRight(),
            swipeRight(),
            swipeRight(),
            swipeRight(),
            swipeRight(),
            swipeRight(),
            swipeRight()
        )

        // try to select bottom nav tab and check the title toolbar

        // check navigation bottom
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        val navs = arrayListOf("Teams", "Favorites", "Matchs")
        val idtxt = arrayListOf(
            R.string.home_actionbar_title_menuteams,
            R.string.home_actionbar_title_menufavorites,
            R.string.home_actionbar_title_menumatch
        )
        var pos = 0
        for (text in navs) {
            val x = allOf(withText(text), isDisplayed())
            onView(x).check(matches(isDisplayed()))
            onView(x).perform(click())
            onView(withText(mActivityRule.activity.resources.getString(idtxt[pos++]))).check(matches(isDisplayed()))
            Thread.sleep(LONG_TIME)
        }
        /////
        clickRecycler(0)
        // select the spinner
        onView(allOf(withText("English Premier League"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // change league into Italian Serie A
        onView(allOf(withText("Italian Serie A"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // perform click on first position
        clickRecycler(0)
        // test on tab PAST
        onView(withText(mActivityRule.activity.getString(R.string.fragment_match_tab_title_past))).perform(click())
        Thread.sleep(MIDDLE_TIME)
    }

    fun clickRecycler(pos: Int) {
        // onclick on recyclerview (first position)
        onView(
            allOf(
                withId(R.id.fragment_match_recyclerholder),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(pos, click()))
        Thread.sleep(ThreadTimes.LONG_TIME)
        Thread.sleep(ThreadTimes.LONG_TIME)
        // back into activity
        onView(isRoot()).perform(pressBack())
        Thread.sleep(MIDDLE_TIME)
    }
}