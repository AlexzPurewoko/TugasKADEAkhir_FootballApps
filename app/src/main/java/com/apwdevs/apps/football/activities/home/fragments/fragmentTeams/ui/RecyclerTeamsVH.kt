package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.apwdevs.apps.football.R.id.adapter_cardview_teams_teambadge
import com.apwdevs.apps.football.R.id.adapter_cardview_teams_teamname
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class RecyclerTeamsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageTeam: ImageView = itemView.find(adapter_cardview_teams_teambadge)
    private val nameTeams: TextView = itemView.find(adapter_cardview_teams_teamname)

    fun bindItem(item: TeamData, listener: (TeamData) -> Unit) {
        nameTeams.text = item.teamName
        Picasso.get().load(item.teamBadge).resize(100, 100).into(imageTeam)
        itemView.setOnClickListener {
            listener(item)
        }
    }
}