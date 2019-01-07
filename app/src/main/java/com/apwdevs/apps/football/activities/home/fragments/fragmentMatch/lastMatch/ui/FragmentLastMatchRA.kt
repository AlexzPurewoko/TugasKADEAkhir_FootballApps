package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData

class FragmentLastMatchRA(
    private val listMatch: List<MatchLeagueData>,
    private val patternDate: String = "dd/MM/yyyy",
    private val listener: (MatchLeagueData) -> Unit
) : RecyclerView.Adapter<FragmentLastMatchVH>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentLastMatchVH {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cardview_matchitem, parent, false)
        return FragmentLastMatchVH(layout)
    }

    override fun getItemCount(): Int = listMatch.size

    override fun onBindViewHolder(holder: FragmentLastMatchVH, position: Int) {
        holder.bindItem(listMatch[position], patternDate, listener)
    }

}