package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import org.jetbrains.anko.AnkoContext

class RecyclerTeamsAdapter(private val teams: List<TeamData>, private val listener: (TeamData) -> Unit) :
    RecyclerView.Adapter<RecyclerTeamsVH>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerTeamsVH {
        val layout = AdapterRecyclerTeams().createView(AnkoContext.create(parent.context, parent, false))
        return RecyclerTeamsVH(layout)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: RecyclerTeamsVH, position: Int) {
        holder.bindItem(teams[position], listener)
    }

}