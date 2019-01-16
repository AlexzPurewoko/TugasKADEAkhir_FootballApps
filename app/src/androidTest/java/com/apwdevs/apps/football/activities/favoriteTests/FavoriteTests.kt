package com.apwdevs.apps.football.activities.favoriteTests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.apwdevs.apps.football.activities.splash.SplashScreenActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteTests {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<SplashScreenActivity> =
        ActivityTestRule(SplashScreenActivity::class.java, false, false)

    @Before
    fun setUp() {

    }

    @Test
    fun testFavorite() {

    }
}