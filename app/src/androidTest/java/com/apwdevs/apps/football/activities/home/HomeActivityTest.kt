package com.apwdevs.apps.football.activities.home

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.ThreadTimes
import com.apwdevs.apps.football.ThreadTimes.LONG_TIME
import com.apwdevs.apps.football.ThreadTimes.MIDDLE_TIME
import com.apwdevs.apps.football.ThreadTimes.SHORT_TIME
import com.apwdevs.apps.football.activities.home.homeUtility.CustomSearchView
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenterImpl
import com.apwdevs.apps.football.utility.ParameterClass
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.Matcher
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
        for ((idx, text) in navs.withIndex()) {
            val x = allOf(withText(text), isDisplayed())
            onView(x).check(matches(isDisplayed()))
            onView(x).perform(click())
            onView(withText(mActivityRule.activity.resources.getString(idtxt[idx]))).check(matches(isDisplayed()))
            Thread.sleep(LONG_TIME)
        }
        /////

        performOnTabNextMatch()
        performOnTabPastMatch()
        performSearchOnTabPastMatch()
        performSearchOnTabNextMatch()

        // SWITCH ANOTHER MENUS (Teams)
        performActionInMenuTeam()
        // SWITCH AND TESTS FAVORITES MENU, ENSURE THE CONDITION OF FAVORITE MENU IS EMPTY, OR IT WILL BE RETURNED ANY EXCEPTION!
        performActionOnFavoriteMenu()
        Thread.sleep(LONG_TIME)
    }

    private fun performActionOnFavoriteMenu() {
        Thread.sleep(MIDDLE_TIME)
        onView(allOf(withText(R.string.my_favorites), isDisplayed())).perform(click())
        onView(withText(R.string.home_actionbar_title_menufavorites)).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
        onView(withText(R.string.fragment_favorite_error_nullmatchs)).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
        onView(withText(R.string.fragment_favorites_tab_title_teams)).perform(click())
        Thread.sleep(SHORT_TIME)
        onView(withText(R.string.fragment_favorite_error_nullteams)).check(matches(isDisplayed()))


    }

    private fun performActionInMenuTeam() {
        onView(allOf(withText(R.string.football_name_tab), isDisplayed())).perform(click())
        onView(withText(R.string.home_actionbar_title_menuteams)).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
        // perform click on recycler View
        clickRecycler(0)
        // select the spinner
        onView(allOf(withText("English Premier League"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // change league into Italian Serie A
        onView(allOf(withText("Italian Serie A"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // perform click on first position
        clickRecycler(0)

        // check the search
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
        onView(withClassName(endsWith("CustomSearchView"))).check(matches(isDisplayed()))
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("Juv"))
        Thread.sleep(SHORT_TIME)
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("At"))
        Thread.sleep(SHORT_TIME)
        // double pressBack
        onView(isRoot()).perform(pressBack(), pressBack())
        Thread.sleep(MIDDLE_TIME)
    }

    private fun performSearchOnTabNextMatch() {
        onView(withText(R.string.fragment_match_tab_title_next)).perform(click())
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
        Thread.sleep(SHORT_TIME)
        var isDisplayed = false
        try {
            onView(withId(R.id.fragment_match_id_appbar)).check(matches(isDisplayed()))
            isDisplayed = true
        } catch (e: Throwable) {
            // ignore this exception
        }
        if (isDisplayed)
            throw Exception("TabLayout is not collapsed!")
        onView(withClassName(endsWith("CustomSearchView"))).check(matches(isDisplayed()))
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("Ful"))
        Thread.sleep(SHORT_TIME)
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("Lei"))
        Thread.sleep(SHORT_TIME)
        // double pressBack
        onView(isRoot()).perform(pressBack(), pressBack())
        Thread.sleep(MIDDLE_TIME)
    }

    private fun performSearchOnTabPastMatch() {
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
        Thread.sleep(SHORT_TIME)
        var isDisplayed = false
        try {
            onView(withId(R.id.fragment_match_id_appbar)).check(matches(isDisplayed()))
            isDisplayed = true
        } catch (e: Throwable) {
            // ignore this exception
        }
        if (isDisplayed)
            throw Exception("TabLayout is not collapsed!")
        onView(withClassName(endsWith("CustomSearchView"))).check(matches(isDisplayed()))
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("Roma"))
        Thread.sleep(SHORT_TIME)
        onView(withClassName(endsWith("CustomSearchView"))).perform(typeSearchViewText("Empoli"))
        Thread.sleep(SHORT_TIME)
        // double pressBack
        onView(isRoot()).perform(pressBack(), pressBack())
        Thread.sleep(MIDDLE_TIME)
    }

    private fun performOnTabNextMatch() {
        clickRecycler(0)
        // select the spinner
        onView(allOf(withText("English Premier League"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // change league into Italian Serie A
        onView(allOf(withText("Italian Serie A"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // perform click on first position
        clickRecycler(0)

        //// check clock button

    }

    private fun performOnTabPastMatch() {
        // test on tab PAST
        onView(withText(R.string.fragment_match_tab_title_past)).perform(click())
        Thread.sleep(MIDDLE_TIME)
        clickRecycler(0)
        // select the spinner
        onView(allOf(withText("English Premier League"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // change league into Italian Serie A
        onView(allOf(withText("Italian Serie A"), isDisplayed())).perform(click())
        Thread.sleep(ThreadTimes.LONG_TIME)
        // perform click on first position
        clickRecycler(0)
    }

    private fun clickRecycler(pos: Int) {
        // onclick on recyclerview (first position)
        onView(
            allOf(
                withId(R.id.fragment_match_recyclerholder),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(pos, click()))
        Thread.sleep(ThreadTimes.LONG_TIME)
        // back into activity
        onView(isRoot()).perform(pressBack())
        Thread.sleep(MIDDLE_TIME)
    }


    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(CustomSearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change the query of searchView"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as CustomSearchView).setQuery(text, false)
            }
        }
    }
}