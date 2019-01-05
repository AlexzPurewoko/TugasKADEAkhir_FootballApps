package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData

class FragmentNextMatchRA(
    private val listMatch: List<MatchLeagueData>,
    private val listener: (MatchLeagueData) -> Unit
) : RecyclerView.Adapter<FragmentNextMatchVH>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentNextMatchVH {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cardview_matchitem, parent, false)
        return FragmentNextMatchVH(layout)
    }

    override fun getItemCount(): Int = listMatch.size

    override fun onBindViewHolder(holder: FragmentNextMatchVH, position: Int) {
        holder.bindItem(listMatch[position], listener)
    }

}