package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.FragmentTeamsModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FragmentTeamsPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var view: FragmentTeamsModel

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: FragmentTeamsPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FragmentTeamsPresenter(ctx, view, apiRepository, gson, true, TestCoroutineContextProvider())
    }

    @Test
    fun getAllTeamsList() {
        val leagueName = "English Premier League"
        val response = FragmentTeamsImpl.getTeamData(buildToURI(leagueName))
        val msg = "This app is in testing mode, so we couldn't use internet connection"

        presenter.getAllTeamsList(leagueName)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).onFullyLoaded(response?.teams!!, msg)
    }

    private fun buildToURI(name: String): String {
        val sbuf = StringBuffer()
        for (i in name) {
            if (i == ' ')
                sbuf.append("%20")
            else
                sbuf.append(i)
        }
        return sbuf.toString()
    }
}