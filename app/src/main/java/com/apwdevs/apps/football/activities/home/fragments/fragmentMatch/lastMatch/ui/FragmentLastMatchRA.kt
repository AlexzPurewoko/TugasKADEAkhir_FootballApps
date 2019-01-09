package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData

class FragmentLastMatchRA(
    private val listMatch: MutableList<MatchLeagueData>,
    private val patternDate: String = "dd/MM/yyyy",
    private val listener: (MatchLeagueData) -> Unit
) : RecyclerView.Adapter<FragmentLastMatchVH>(), Filterable {

    private var mFilteredLastMatch: MutableList<MatchLeagueData> = listMatch

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charString = constraint.toString()

            mFilteredLastMatch = if (charString.isEmpty())
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
            filterResults.values = mFilteredLastMatch
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mFilteredLastMatch = results?.values as MutableList<MatchLeagueData>
            notifyDataSetChanged()
        }

    }


    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentLastMatchVH {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cardview_matchitem, parent, false)
        return FragmentLastMatchVH(layout)

    }


    override fun getItemCount(): Int = mFilteredLastMatch.size

    override fun onBindViewHolder(holder: FragmentLastMatchVH, position: Int) {
        holder.bindItem(mFilteredLastMatch[position], patternDate, listener)
    }

}