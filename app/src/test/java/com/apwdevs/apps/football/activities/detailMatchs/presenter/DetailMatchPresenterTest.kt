package com.apwdevs.apps.football.activities.detailMatchs.presenter

import android.content.Context
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DataPropertyRecycler
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DetailMatchDataClass
import com.apwdevs.apps.football.activities.detailMatchs.dataController.TeamPropData
import com.apwdevs.apps.football.activities.detailMatchs.ui.DetailMatchModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.AvailableDataUpdates
import com.apwdevs.apps.football.utility.TestCoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var model: DetailMatchModel

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var preventUpdate: AvailableDataUpdates

    private lateinit var presenter: DetailMatchPresenter
    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(ctx, apiRepository, model, gson, true, TestCoroutineContextProvider())
    }

    @Test
    fun getData() {
        val eventMatchId = "576675"
        val listDetailMatch: MutableList<DetailMatchDataClass> = mutableListOf()
        val listTeamPropData: MutableList<TeamPropData> = mutableListOf()
        val listDataPropRecycler: MutableList<DataPropertyRecycler> = mutableListOf()
        val msg = "This app is in testing mode, so we couldn't use internet connection"

        runBlocking {

            /*Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailMatchResponse::class.java
                )
            ).thenReturn(response)*/

            //listDetailMatch.add(mock(DetailMatchDataClass::class.java))

            val responseData = DetailMatchPresenterImpl.getData(eventMatchId)!!
            listDetailMatch.addAll(responseData.events)
            listTeamPropData.addAll(DetailMatchPresenterImpl.getTeamDataInMatch(responseData))
            listDataPropRecycler.addAll(presenter.getDataRecycler(responseData.events[0]))
            presenter.getData(eventMatchId)

            Mockito.verify(model).hideLoading()
            Mockito.verify(model).showLoading()
            Mockito.verify(model).onSuccessLoadingData(listDetailMatch[0], listTeamPropData, listDataPropRecycler, msg)
        }
    }
}