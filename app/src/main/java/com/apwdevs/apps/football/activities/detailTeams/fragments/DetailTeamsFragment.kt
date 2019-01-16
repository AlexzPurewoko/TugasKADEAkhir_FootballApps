package com.apwdevs.apps.football.activities.detailTeams.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerCarry
import com.apwdevs.apps.football.activities.detailTeams.fragments.adapter.DetailTeamRA
import com.apwdevs.apps.football.utility.ParameterClass
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailTeamsFragment : Fragment(), AnkoComponent<Context> {

    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: DetailTeamRA

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val item = arguments?.getSerializable(ParameterClass.KEY_ARGUMRNT_RECYCLER_DETAIL) as DetailRecyclerCarry
        recyclerAdapter = DetailTeamRA(item.dataSets)
        recyclerView.adapter = recyclerAdapter

    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            recyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    companion object {
        fun newInstance(dataSets: DetailRecyclerCarry): DetailTeamsFragment {
            val fragment = DetailTeamsFragment()
            val extras = Bundle()
            extras.putSerializable(ParameterClass.KEY_ARGUMRNT_RECYCLER_DETAIL, dataSets)
            fragment.arguments = extras
            return fragment
        }
    }
}