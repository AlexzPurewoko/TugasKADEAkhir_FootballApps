package com.apwdevs.apps.football.activities.splash

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.apwdevs.apps.football.ThreadTimes
import com.apwdevs.apps.football.utility.ParameterClass
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    // test splash and its loading
    // if loading data success, it belongs to move into next activity and exit

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
        //onView(withText("Loading Data...")).check(matches(isDisplayed()))
        Thread.sleep(ThreadTimes.LONG_TIME)
    }
}