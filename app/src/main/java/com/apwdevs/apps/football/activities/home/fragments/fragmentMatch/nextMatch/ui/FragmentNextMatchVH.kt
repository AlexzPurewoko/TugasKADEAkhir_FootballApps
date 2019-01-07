package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.apwdevs.apps.football.R.id.*
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.utility.AddToCalendar
import com.apwdevs.apps.football.utility.MyDate

class FragmentNextMatchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val date: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_date)
    private val time: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_time)
    private val clockAdd: ImageButton = itemView.findViewById(adapter_cardview_footballmatch_id_clock_add)
    private val homeNameTeam: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_teamname_left)
    private val awayNameTeam: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_teamname_right)
    private val homeScoreTeam: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_teamscore_left)
    private val awayScoreTeam: TextView = itemView.findViewById(adapter_cardview_footballmatch_id_teamscore_right)

    fun bindItem(item: MatchLeagueData, listener: (MatchLeagueData) -> Unit) {
        val currentCalendar = MyDate.getCalendarInGMT7(item.time, item.date, "dd/MM/yyyy")
        date.text = MyDate.getDateFromCalendar(currentCalendar)//MyDate.getDate(item.date, "dd/MM/yyyy")
        time.text = MyDate.getTimeFromCalendar(currentCalendar)//MyDate.getTimeInGMT7(item.time)
        homeNameTeam.text = item.homeTeamName
        homeScoreTeam.text = item.homeTeamScore
        awayNameTeam.text = item.awayTeamName
        awayScoreTeam.text = item.awayTeamScore
        clockAdd.setOnClickListener {
            AddToCalendar.add(
                itemView.context,
                "${item.homeTeamName} vs ${item.awayTeamName}",
                "Next Match League! ${item.homeTeamName} vs ${item.awayTeamName}. Please reminder me!",
                item.time!!,
                item.date!!
            )
        }
        itemView.setOnClickListener {
            listener(item)
        }
    }
}