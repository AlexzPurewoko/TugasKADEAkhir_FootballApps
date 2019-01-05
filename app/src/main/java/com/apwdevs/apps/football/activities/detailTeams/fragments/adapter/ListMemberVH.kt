package com.apwdevs.apps.football.activities.detailTeams.fragments.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.apwdevs.apps.football.R.id.*
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData
import com.squareup.picasso.Picasso

class ListMemberVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val playerImage: ImageView = itemView.findViewById(adapter_list_member_teams_photos)
    private val playerName: TextView = itemView.findViewById(adapter_list_member_teams_playername)
    private val playerPosition: TextView = itemView.findViewById(adapter_list_member_teams_playerposition)

    fun bindItem(player: TeamMemberShortData) {
        Picasso.get().load(player.playerPhotosUrl).resize(100, 100).into(playerImage)
        playerName.text = player.playerName
        playerPosition.text = player.playerPosition
    }
}