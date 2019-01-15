package com.apwdevs.apps.football.activities.splash.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.splash.ui.SplashModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
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

    private lateinit var presenter: SplashPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SplashPresenter(ctx, view, apiRepository, gson, true, TestCoroutineContextProvider())
    }

    @Test
    fun getLeagueList() {
        val resp = SplashPresenterImpl.getData()
        presenter.getLeagueList()
        Mockito.verify(view).onLoadingStarted()
        Mockito.verify(view).onLoadingSuccesfully(resp.leagues)
    }
}