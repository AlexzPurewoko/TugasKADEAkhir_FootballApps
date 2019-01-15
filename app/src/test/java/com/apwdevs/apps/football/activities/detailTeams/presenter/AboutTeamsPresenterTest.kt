package com.apwdevs.apps.football.activities.detailTeams.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.ui.AboutTeamsModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AboutTeamsPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var view: AboutTeamsModel

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: AboutTeamsPresenter

    private val contextPool = TestCoroutineContextProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = AboutTeamsPresenter(ctx, view, apiRepository, gson, true, contextPool)
    }

    @Test
    fun getDataBehaviour() {
        val teamId = "133604"
        val teamsResponse = AboutTeamsPresenterImpl.getAboutTeams(teamId)
        val playerMemberResponse = AboutTeamsPresenterImpl.getPlayerList(teamId)
        val listRecycler = mutableListOf<DetailRecyclerData>()
        val msg = "This app is in testing mode, so we couldn't use internet connection"

        runBlocking {
            presenter.getDataBehaviour(teamId)
            presenter.getRecyclerDataSets(listRecycler)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view)
                .onLoadFinished(teamsResponse!!.teams[0], playerMemberResponse!!.player, listRecycler, msg)

        }
    }


}