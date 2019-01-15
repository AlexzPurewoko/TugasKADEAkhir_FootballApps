package com.apwdevs.apps.football

import android.content.Intent
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
import com.apwdevs.apps.football.activities.splash.SplashScreenActivity
import com.apwdevs.apps.football.utility.ParameterClass
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BehaviorTest {
    companion object {
        const val LONG_TIME = 3000L
        const val MIDDLE_TIME = 2000L
        const val SHORT_TIME = 1500L
    }

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<SplashScreenActivity> =
        ActivityTestRule(SplashScreenActivity::class.java, false, false)

    @Before
    fun setUp() {
        val i = Intent()
        i.putExtra(ParameterClass.KEY_IS_APP_TESTING, true)
        mActivityRule.launchActivity(i)
    }

    @Test
    fun test() {
        testSplashScreen()
        Thread.sleep(MIDDLE_TIME)
        testHomeActivity()
        Thread.sleep(MIDDLE_TIME)

    }

    private fun testHomeActivity() {
        /*onView(isRoot()).perform(swipeLeft(), swipeLeft(), swipeLeft(), swipeLeft(), swipeLeft(), swipeLeft())
        Thread.sleep(SHORT_TIME)
        onView(isRoot()).perform(swipeRight(), swipeRight(), swipeRight(), swipeRight(), swipeRight(), swipeRight())
        Thread.sleep(MIDDLE_TIME)
        // check navigation bottom
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        val navs = arrayListOf<String>("Teams", "Favorites", "Matchs")
        for(text in navs){
            val x = allOf(withText(text), isDisplayed())
            onView(x).check(matches(isDisplayed()))
            onView(x).perform(click())
            Thread.sleep(SHORT_TIME)
        }*/
        // check toolbar
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.fragment_match_refreshswipe), isDisplayed())).perform(swipeDown())
        Thread.sleep(SHORT_TIME)
        onView(withText("Cache is found! if you need to update data, please swipe down about 1x to update data")).check(
            matches(isDisplayed())
        )
        //onView(allOf(withId(R.id.fragment_match_progressbar), isDisplayed())).check(matches(isDisplayed()))
        Thread.sleep(SHORT_TIME)
        onView(allOf(withId(R.id.fragment_match_spinner), isDisplayed())).perform(click())
        Thread.sleep(SHORT_TIME)
        onView(withText("Italian Serie A")).perform(click())
        onView(allOf(withId(R.id.fragment_match_refreshswipe), isDisplayed())).check(matches(isDisplayed()))


    }

    private fun testSplashScreen() {
        /*while (true){
            try {
                onView(withId(R.id.splash_relative_loading)).check(matches(isDisplayed()))
                break
            }catch (e: Exception){
            }
        }*/

        onView(withText("Loading Data...")).check(matches(isDisplayed()))
        onView(withId(R.id.splash_progress_bar)).check(matches(isDisplayed()))

        // waiting the process to be done
        /*while (true){
            try {
                onView(withId(R.id.splash_relative_loading)).check(matches(isDisplayed()))
            } catch (e: Exception){
                break
            }
        }*/
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
}