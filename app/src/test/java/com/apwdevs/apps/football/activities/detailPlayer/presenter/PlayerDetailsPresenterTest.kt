package com.apwdevs.apps.football.activities.detailPlayer.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailPlayer.ui.PlayerDetailsModel
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerDetailsPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var view: PlayerDetailsModel

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: PlayerDetailsPresenter

    private val contextPool = TestCoroutineContextProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailsPresenter(ctx, apiRepository, view, gson, true, contextPool)
    }

    @Test
    fun getDetails() {
        val playerId = "34145444"
        val playerRespnse = PlayerTeamsPresenterImpl.getDetails(playerId)
        val listRecycler = mutableListOf<DetailRecyclerData>()
        val msg = "This app is in testing mode, so we couldn't use internet connection"

        runBlocking {
            presenter.getPlayerDetails(playerId)
            presenter.getRecyclerData(listRecycler, playerRespnse!!.players[0])
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).onDataLoadFinished(playerRespnse.players[0], listRecycler, msg)

        }
    }

}