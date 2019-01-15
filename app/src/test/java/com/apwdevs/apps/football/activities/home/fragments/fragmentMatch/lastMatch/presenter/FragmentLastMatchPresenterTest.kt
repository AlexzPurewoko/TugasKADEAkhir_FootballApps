package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.impl.FragmentMatchPresenterImpl
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui.FragmentLastMatchModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FragmentLastMatchPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var view: FragmentLastMatchModel

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: FragmentLastMatchPresenter

    private val contextPool = TestCoroutineContextProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FragmentLastMatchPresenter(ctx, view, gson, apiRepository, true, contextPool)
    }

    @Test
    fun getMatch() {
        val leagueId = "4328"
        val response = FragmentMatchPresenterImpl.getDataLastMatch(leagueId)
        val msg = "This app is in testing mode, so we couldn't use internet connection"

        presenter.getMatch(leagueId)
        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataSucceeded(response!!, msg)
    }
}