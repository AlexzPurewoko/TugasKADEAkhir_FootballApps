package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData

class FragmentNextMatchRA(
    private val listMatch: MutableList<MatchLeagueData>,
    private val listener: (MatchLeagueData) -> Unit
) : RecyclerView.Adapter<FragmentNextMatchVH>(), Filterable {


    private var mFilteredListNextMatch: MutableList<MatchLeagueData> = listMatch

    init {
        setHasStableIds(true)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charString = constraint.toString()

            mFilteredListNextMatch = if (charString.isEmpty())
                listMatch else {

                val filteredList = mutableListOf<MatchLeagueData>()
                filteredList.clear()
                for (matchData in listMatch) {
                    if (matchData.homeTeamName!!.contains(charString, true) || matchData.awayTeamName!!.contains(
                            charString,
                            true
                        )
                    )
                        filteredList.add(matchData)
                }

                filteredList
            }

            val filterResults = FilterResults()
            filterResults.values = mFilteredListNextMatch
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mFilteredListNextMatch = results?.values as MutableList<MatchLeagueData>
            notifyDataSetChanged()
        }

    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentNextMatchVH {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cardview_matchitem, parent, false)
        return FragmentNextMatchVH(layout)
    }

    override fun getItemCount(): Int = mFilteredListNextMatch.size

    override fun onBindViewHolder(holder: FragmentNextMatchVH, position: Int) {
        holder.bindItem(mFilteredListNextMatch[position], listener)
    }

}