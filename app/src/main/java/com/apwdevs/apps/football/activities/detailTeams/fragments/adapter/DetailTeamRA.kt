package com.apwdevs.apps.football.activities.detailTeams.fragments.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData

class DetailTeamRA(private val recyclerDataSets: List<DetailRecyclerData>) : RecyclerView.Adapter<DetailTeamVH>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTeamVH {
        return DetailTeamVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_detail_recycler_cardopt,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = recyclerDataSets.size

    override fun onBindViewHolder(holder: DetailTeamVH, position: Int) {
        holder.bindItem(recyclerDataSets[position])
    }
}