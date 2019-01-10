package com.apwdevs.apps.football.activities.detailMatchs.ui

import com.apwdevs.apps.football.activities.detailMatchs.dataController.DataPropertyRecycler
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DetailMatchDataClass
import com.apwdevs.apps.football.activities.detailMatchs.dataController.TeamPropData

interface DetailMatchModel {
    fun showLoading()
    fun hideLoading()
    fun onSuccessLoadingData(
        matchData: DetailMatchDataClass,
        teamProps: List<TeamPropData>,
        dataRecycler: MutableList<DataPropertyRecycler>,
        msg: String
    )

    fun onFailedLoadingData(what: String)
}