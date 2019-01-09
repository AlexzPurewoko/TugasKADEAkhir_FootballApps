package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import org.jetbrains.anko.AnkoContext

class RecyclerTeamsAdapter(private val teams: MutableList<TeamData>, private val listener: (TeamData) -> Unit) :
    RecyclerView.Adapter<RecyclerTeamsVH>(), Filterable {

    private var mFilteredTeams: MutableList<TeamData> = teams

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charString = constraint.toString()

            mFilteredTeams = if (charString.isEmpty())
                teams else {

                val filteredList = mutableListOf<TeamData>()
                filteredList.clear()
                for (team in teams) {
                    if (team.teamName?.contains(charString, true)!!)
                        filteredList.add(team)
                }

                filteredList
            }

            val filterResults = FilterResults()
            filterResults.values = mFilteredTeams
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mFilteredTeams = results?.values as MutableList<TeamData>
            notifyDataSetChanged()
        }

    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerTeamsVH {
        val layout = AdapterRecyclerTeams().createView(AnkoContext.create(parent.context, parent, false))
        return RecyclerTeamsVH(layout)
    }

    override fun getItemCount(): Int = mFilteredTeams.size

    override fun onBindViewHolder(holder: RecyclerTeamsVH, position: Int) {
        holder.bindItem(mFilteredTeams[position], listener)
    }

}