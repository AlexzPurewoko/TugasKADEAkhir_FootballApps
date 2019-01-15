package com.apwdevs.apps.football.activities.detailTeams.fragments.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData

class ListMemberRA(
    private val players: List<TeamMemberShortData>,
    private val isTesting: Boolean,
    private val listener: (TeamMemberShortData) -> Unit
) : RecyclerView.Adapter<ListMemberVH>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMemberVH {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_member_teams, parent, false)
        layout.setOnClickListener {
            listener(players[viewType])
        }
        return ListMemberVH(layout)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ListMemberVH, position: Int) {
        holder.bindItem(players[position], isTesting)
    }

}