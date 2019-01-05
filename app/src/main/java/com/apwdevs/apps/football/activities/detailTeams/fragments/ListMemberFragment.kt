package com.apwdevs.apps.football.activities.detailTeams.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apwdevs.apps.football.activities.detailPlayer.PlayerDetails
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortDataResponse
import com.apwdevs.apps.football.activities.detailTeams.fragments.adapter.ListMemberRA
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class ListMemberFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ListMemberRA


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val players =
            arguments?.getSerializable(ParameterClass.KEY_ARGUMENT_LIST_MEMBER_PLAYER) as TeamMemberShortDataResponse
        val leagues = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        val teamId = arguments?.getString(ParameterClass.ID_SELECTED_TEAMS)
        recyclerAdapter = ListMemberRA(players.player) {
            toast("selected name ${it.playerName} id : ${it.playerId}").show()
            startActivity(
                intentFor<PlayerDetails>(
                    ParameterClass.ID_SELECTED_PLAYERS to it.playerId,
                    ParameterClass.LIST_LEAGUE_DATA to leagues.leagues,
                    ParameterClass.ID_SELECTED_TEAMS to teamId
                ).clearTask()
            )
        }
        recyclerView.adapter = recyclerAdapter

    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            recyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    companion object {
        fun newInstance(
            players: List<TeamMemberShortData>,
            leagues: MutableList<TeamLeagueData>,
            teamId: String
        ): ListMemberFragment {
            val fragment = ListMemberFragment()
            val extras = Bundle()
            extras.putSerializable(ParameterClass.KEY_ARGUMENT_LIST_MEMBER_PLAYER, TeamMemberShortDataResponse(players))
            extras.putSerializable(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(leagues))
            extras.putString(ParameterClass.ID_SELECTED_TEAMS, teamId)
            fragment.arguments = extras
            return fragment
        }
    }
}