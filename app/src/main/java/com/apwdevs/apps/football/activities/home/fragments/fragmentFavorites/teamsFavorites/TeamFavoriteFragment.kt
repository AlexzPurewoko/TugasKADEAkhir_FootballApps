package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.teamsFavorites

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailTeams.AboutTeams
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.dataController.TeamData
import com.apwdevs.apps.football.activities.home.fragments.fragmentTeams.ui.RecyclerTeamsAdapter
import com.apwdevs.apps.football.activities.home.homeUtility.FragmentHomeCallback
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.database.TeamFavoriteData
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.gone
import com.apwdevs.apps.football.utility.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamFavoriteFragment : Fragment(), AnkoComponent<ViewGroup>, FragmentTeamModel, FragmentHomeCallback {

    private lateinit var cardWhenNull: CardView
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerAdapter: RecyclerTeamsAdapter
    private lateinit var presenter: FragmentTeamPresenter
    private lateinit var leagues: LeagueResponse
    private val listFavoriteTeams: MutableList<TeamData> = mutableListOf()
    private val teamsFavoriteOrig: MutableList<TeamFavoriteData> = mutableListOf()
    private var isTesting: Boolean = false


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        leagues = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        isTesting = arguments?.getBoolean(ParameterClass.KEY_IS_APP_TESTING)!!
        recyclerAdapter = RecyclerTeamsAdapter(listFavoriteTeams, isTesting) {
            startActivity(
                intentFor<AboutTeams>(
                    ParameterClass.ID_SELECTED_TEAMS to it.teamId,
                    ParameterClass.LIST_LEAGUE_DATA to leagues
                ).clearTask()
            )
        }
        recyclerView.adapter = recyclerAdapter
        presenter = FragmentTeamPresenter(requireContext(), this)
        swipeRefreshLayout.onRefresh {
            presenter.getFromDatabase()
        }
        presenter.getFromDatabase()
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onLoaded(listMatch: MutableList<TeamData>, listFavorite: List<TeamFavoriteData>) {
        listFavoriteTeams.clear()
        teamsFavoriteOrig.clear()
        teamsFavoriteOrig.addAll(listFavorite)
        listFavoriteTeams.addAll(listMatch)
        recyclerAdapter.notifyDataSetChanged()
        if (listFavoriteTeams.isEmpty()) {
            recyclerView.gone()
            cardWhenNull.visible()
        } else {
            recyclerView.visible()
            cardWhenNull.gone()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext(), container!!))
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(10)
            orientation = LinearLayout.VERTICAL
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_red_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_blue_dark,
                    android.R.color.holo_green_dark
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerView = recyclerView {
                        layoutManager = LinearLayoutManager(requireContext())
                    }.lparams(width = matchParent, height = wrapContent)

                    cardWhenNull = cardView {
                        cardElevation = 4.0f
                        visibility = View.VISIBLE
                        val padding = dip(10)
                        setContentPadding(padding, padding, padding, padding)
                        textView {
                            text = getSpannable()
                            gravity = Gravity.CENTER
                        }.lparams(width = matchParent, height = wrapContent)

                    }.lparams(width = matchParent, height = wrapContent) {

                    }

                }
            }
        }

    }

    private fun getSpannable(): CharSequence? {
        val result = SpannableStringBuilder(getString(R.string.fragment_favorite_error_nullteams))
        result.setSpan(StyleSpan(Typeface.ITALIC), 0, result.length, 0)
        return result

    }

    override fun transactionData(what: String) {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        recyclerAdapter.filter.filter(newText)
        return true
    }

    override fun onActionViewCollapsed() {

    }

    override fun onActionViewExpanded() {

    }

    override fun onDetachedFromWindow() {

    }


    companion object {
        fun newInstance(leagues: LeagueResponse, isTesting: Boolean): TeamFavoriteFragment {
            val fragment = TeamFavoriteFragment()
            val extras = Bundle()
            extras.putSerializable(ParameterClass.LIST_LEAGUE_DATA, leagues)
            extras.putBoolean(ParameterClass.KEY_IS_APP_TESTING, isTesting)
            fragment.arguments = extras
            return fragment
        }
    }

}