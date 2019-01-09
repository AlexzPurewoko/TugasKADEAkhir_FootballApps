package com.apwdevs.apps.football.activities.home.fragments.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FragmentUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)

            val actionBarSizeAttrs = ctx.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
            val actBarSizes = actionBarSizeAttrs.getDimension(0, 0.0f)
            actionBarSizeAttrs.recycle()
            orientation = LinearLayout.VERTICAL
            topPadding = dip(10)
            bottomPadding = Math.round(actBarSizes)
            leftPadding = dip(10)
            rightPadding = dip(10)
            spinner {
                id = R.id.fragment_match_spinner
            }
            swipeRefreshLayout {
                id = R.id.fragment_match_refreshswipe
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    bottomPadding = Math.round(ctx.resources.getDimension(R.dimen.dimen_fragment_home_vpager_bottom))

                    recyclerView {
                        id = R.id.fragment_match_recyclerholder
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar {
                        id = R.id.fragment_match_progressbar
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

}