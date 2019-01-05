package com.apwdevs.apps.football.activities.splash.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.ui.SplashModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SplashPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var view: SplashModel

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SplashPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SplashPresenter(ctx, view, apiRepository, gson, true, TestCoroutineContextProvider())
    }

    @Test
    fun getLeagueList() {
        val teams: MutableList<TeamLeagueData> = mutableListOf()
        val response = LeagueResponse(teams)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueList()

            Mockito.verify(view).onLoadingStarted()
            Mockito.verify(view).onLoadingSuccesfully(teams)
        }
    }
}