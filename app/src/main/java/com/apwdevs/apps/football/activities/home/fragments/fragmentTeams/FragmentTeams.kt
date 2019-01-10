package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams

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
import com.apwdevs.apps.football.activities.detailTeams.AboutTeams
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.presenter.FragmentTeamsPresenter
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.FragmentTeamsModel
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.RecyclerTeamsAdapter
import com.apwdevs.apps.football.activities.home.fragments.ui.FragmentUI
import com.apwdevs.apps.football.activities.home.homeUtility.FragmentHomeCallback
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.invisible
import com.apwdevs.apps.football.utility.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FragmentTeams : Fragment(), FragmentTeamsModel, FragmentHomeCallback {


    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapterRecyclerView: RecyclerTeamsAdapter
    private var listTeam: MutableList<TeamData> = mutableListOf()
    private lateinit var leagues: MutableList<TeamLeagueData>
    private lateinit var allLeagueNames: MutableList<String>
    private lateinit var presenter: FragmentTeamsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = FragmentUI().createView(AnkoContext.create(requireContext()))
        spinner = layout.find(R.id.fragment_match_spinner)
        swipeRefresh = layout.find(R.id.fragment_match_refreshswipe)
        recyclerView = layout.find(R.id.fragment_match_recyclerholder)
        progressBar = layout.find(R.id.fragment_match_progressbar)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val item = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse

        leagues = item.leagues as MutableList<TeamLeagueData>
        getNameLeagues()
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, allLeagueNames)

        adapterRecyclerView = RecyclerTeamsAdapter(listTeam) {
            startActivity(
                intentFor<AboutTeams>(
                    ParameterClass.ID_SELECTED_TEAMS to it.teamId,
                    ParameterClass.LIST_LEAGUE_DATA to leagues
                ).clearTask()
            )
        }
        recyclerView.adapter = adapterRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = FragmentTeamsPresenter(requireContext(), this, apiRepository, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getAllTeamsList(leagues[spinner.selectedItemPosition].leagueName!!)
            }

        }
        swipeRefresh.onRefresh {
            presenter.getAllTeamsList(leagues[spinner.selectedItemPosition].leagueName!!)
        }
    }

    private fun getNameLeagues() {
        allLeagueNames = mutableListOf()
        for (selectedLeague in leagues) {
            allLeagueNames.add(selectedLeague.leagueName ?: "null")
        }
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onNotLoaded(msg: String) {
        swipeRefresh.snackbar("Error : $msg").show()
    }

    override fun onFullyLoaded(teams: List<TeamData>, msg: String) {
        swipeRefresh.isRefreshing = false
        listTeam.clear()
        listTeam.addAll(teams)
        adapterRecyclerView.notifyDataSetChanged()
        swipeRefresh.snackbar(msg)
    }

    override fun transactionData(what: String) {
        //swipeRefresh.snackbar(what).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapterRecyclerView.filter.filter(newText)
        return true
    }

    override fun onActionViewCollapsed() {

    }

    override fun onActionViewExpanded() {

    }

    override fun onDetachedFromWindow() {

    }

    companion object {
        fun newInstance(leagues: List<TeamLeagueData>): FragmentTeams {
            val fragment = FragmentTeams()
            val args = Bundle()
            args.putSerializable(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(leagues))
            fragment.arguments = args
            return fragment
        }
    }
}