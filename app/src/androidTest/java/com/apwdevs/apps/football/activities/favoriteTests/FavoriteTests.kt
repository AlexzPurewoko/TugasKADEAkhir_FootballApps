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
    
    // THIS CLASS IS JUST PERFORM Favorite Testing, no another action 
    
    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<SplashScreenActivity> =
        ActivityTestRule(SplashScreenActivity::class.java, false, false)
    
    private val dataResponse: MutableList<TeamLeagueData> = mutableListOf()
    @Before
    fun setUp() {
        val response = SplashPresenterImpl.getData()
        dataResponse.addAll(response.leagues)
    }

    @Test
    fun testFavorite() {
        val i = Intent()
        i.putExtra(ParameterClass.KEY_IS_APP_TESTING, true)
        i.putExtra(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(dataResponse))
        mActivityRule.launchActivity(i)
        Thread.sleep(ThreadTimes.MIDDLE_TIME)
        
        checkFavoriteMenuMustBeNull()
        performAddFavoriteInLastMatch()
        performAddFavoriteInNextMatch()
        performAddFavoriteInTeamsMenu()
        performClearFavoriteMenuMatchTab()
        performClearFavoriteMenuTeamsTab()
        checkFavoriteMenuMustBeNull()
    }
    fun checkFavoriteMenuMustBeNull(){
        
    }
    fun performAddFavoriteInLastMatch(){
        
    }
    fun performAddFavoriteInNextMatch(){
        
    }
    fun performAddFavoriteInTeamsMenu(){
        
    }
    fun performClearFavoriteMenuMatchTab(){
        
    }
    fun performClearFavoriteMenuTeamsTab(){
        
    }
}
