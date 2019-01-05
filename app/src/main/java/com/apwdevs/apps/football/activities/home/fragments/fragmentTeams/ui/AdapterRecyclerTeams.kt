package com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.apwdevs.apps.football.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class AdapterRecyclerTeams : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(10)
                leftMargin = dip(10)
                rightMargin = dip(10)
            }
            cardElevation = 4.0f
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.adapter_cardview_teams_teambadge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.adapter_cardview_teams_teamname
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }

    }
}