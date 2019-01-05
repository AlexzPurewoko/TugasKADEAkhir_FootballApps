package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailMatchs.DetailMatchActivity
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueResponse
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.presenter.FragmentLastMatchPresenter
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui.FragmentLastMatchModel
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui.FragmentLastMatchRA
import com.apwdevs.apps.football.activities.home.fragments.ui.FragmentUI
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.invisible
import com.apwdevs.apps.football.utility.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FragmentLastMatch : Fragment(), FragmentLastMatchModel {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBarr: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var recyclerAdapter: FragmentLastMatchRA
    private lateinit var presenter: FragmentLastMatchPresenter

    private lateinit var leagues: MutableList<TeamLeagueData>
    private lateinit var allLeagueNames: MutableList<String>

    private var allMatchInLeague: MutableList<MatchLeagueData> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = FragmentUI().createView(AnkoContext.create(requireContext()))
        spinner = layout.find(R.id.fragment_match_spinner)
        swipeRefresh = layout.find(R.id.fragment_match_refreshswipe)
        recyclerView = layout.find(R.id.fragment_match_recyclerholder)
        progressBarr = layout.find(R.id.fragment_match_progressbar)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val item = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse

        leagues = item.leagues as MutableList<TeamLeagueData>
        getNameLeagues()
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, allLeagueNames)

        recyclerAdapter = FragmentLastMatchRA(allMatchInLeague) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    ParameterClass.ID_EVENT_MATCH_SELECTED to it.eventId,
                    ParameterClass.ID_SELECTED_LEAGUE_KEY to leagues[spinner.selectedItemPosition].leagueId,
                    ParameterClass.NAME_LEAGUE_KEY to leagues[spinner.selectedItemPosition].leagueName,
                    ParameterClass.LIST_LEAGUE_DATA to item
                ).clearTask().clearTop()
            )
        }
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = FragmentLastMatchPresenter(requireContext(), this, gson, apiRepository)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getMatch(leagues[spinner.selectedItemPosition].leagueId!!)
            }

        }
        swipeRefresh.onRefresh {
            presenter.getMatch(leagues[spinner.selectedItemPosition].leagueId!!)
        }
    }

    private fun getNameLeagues() {
        allLeagueNames = mutableListOf()
        for (selectedLeague in leagues) {
            allLeagueNames.add(selectedLeague.leagueName ?: "null")
        }
    }

    override fun onShowLoading() {
        progressBarr.visible()
    }

    override fun onHideLoading() {
        progressBarr.invisible()
    }

    override fun onRequestFailed(msg: String) {
        alert(msg, "Error") {
            iconResource = R.drawable.ic_report_problem
            negativeButton("Quit") {
                it.dismiss()
            }
        }.show()
    }

    override fun onDataSucceeded(leagues: MatchLeagueResponse) {
        swipeRefresh.isRefreshing = false
        allMatchInLeague.clear()
        allMatchInLeague.addAll(leagues.events)
        recyclerAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(leagues: LeagueResponse): FragmentLastMatch {
            val fragment = FragmentLastMatch()
            val extras = Bundle()
            extras.putSerializable(ParameterClass.LIST_LEAGUE_DATA, leagues)
            fragment.arguments = extras
            return fragment
        }
    }
}