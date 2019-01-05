package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.utility.MyDate
import com.apwdevs.apps.football.utility.gone

class FragmentLastMatchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val date: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_date)
    private val time: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_time)
    private val clockAdd: ImageButton = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_clock_add)
    private val homeNameTeam: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_teamname_left)
    private val awayNameTeam: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_teamname_right)
    private val homeScoreTeam: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_teamscore_left)
    private val awayScoreTeam: TextView = itemView.findViewById(R.id.adapter_cardview_footballmatch_id_teamscore_right)

    fun bindItem(item: MatchLeagueData, listener: (MatchLeagueData) -> Unit) {
        clockAdd.gone()
        date.text = MyDate.getDate(item.date, "dd/MM/yyyy")
        time.text = MyDate.getTimeInGMT7(item.time)
        homeNameTeam.text = item.homeTeamName
        homeScoreTeam.text = item.homeTeamScore
        awayNameTeam.text = item.awayTeamName
        awayScoreTeam.text = item.awayTeamScore
        itemView.setOnClickListener {
            listener(item)
        }
    }
}