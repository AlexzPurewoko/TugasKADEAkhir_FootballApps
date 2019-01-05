package com.apwdevs.apps.football.activities.detailTeams.fragments.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.R.id.adapter_detail_recycler_cardopt_property
import com.apwdevs.apps.football.R.id.adapter_detail_recycler_cardopt_value
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.dataController.PropertyRecyclerType
import com.apwdevs.apps.football.utility.gone
import com.squareup.picasso.Picasso
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.centerHorizontally

class DetailTeamVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val property: TextView = itemView.findViewById(adapter_detail_recycler_cardopt_property)
    private val layoutHolder: RelativeLayout = itemView.findViewById(adapter_detail_recycler_cardopt_value)

    fun bindItem(recycler: DetailRecyclerData) {
        property.text = recycler.propertyName
        when (recycler.propertyType) {
            PropertyRecyclerType.PROPERTY_IMAGE -> {
                val img = getImageView()
                layoutHolder.addView(img)
                Picasso.get().load(recycler.value).into(img)
            }
            PropertyRecyclerType.PROPERTY_ONLY_TEXT_VALUE -> {
                val text = getTextView()
                layoutHolder.addView(text)
                text.text = recycler.value
            }
            PropertyRecyclerType.PROPERTY_INDEPENDENT -> {
                property.setTextColor(Color.WHITE)
                property.gravity = Gravity.CENTER
                itemView.backgroundColorResource = R.color.colorPrimary
                layoutHolder.gone()
            }
        }
    }

    fun getTextView(): TextView {
        val text = TextView(itemView.context)
        text.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        text.gravity = Gravity.LEFT
        return text
    }

    fun getImageView(): ImageView {
        val img = ImageView(itemView.context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.centerHorizontally()
        img.layoutParams = params
        return img
    }
}