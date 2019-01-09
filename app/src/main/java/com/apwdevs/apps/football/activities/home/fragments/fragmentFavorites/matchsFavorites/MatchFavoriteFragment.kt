package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.matchsFavorites

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
import com.apwdevs.apps.football.activities.detailMatchs.DetailMatchActivity
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.dataController.MatchLeagueData
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.ui.FragmentLastMatchRA
import com.apwdevs.apps.football.activities.home.homeUtility.FragmentHomeCallback
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.database.MatchFavoriteData
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.gone
import com.apwdevs.apps.football.utility.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFavoriteFragment : Fragment(), AnkoComponent<ViewGroup>, FragmentMatchModel, FragmentHomeCallback {

    private lateinit var cardWhenNull: CardView
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerAdapter: FragmentLastMatchRA
    private lateinit var presenter: FragmentMatchPresenter
    private lateinit var leagues: LeagueResponse
    private val listFavoriteMatch: MutableList<MatchLeagueData> = mutableListOf()
    private val matchFavoriteOrig: MutableList<MatchFavoriteData> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        leagues = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        recyclerAdapter = FragmentLastMatchRA(listFavoriteMatch, "yyyy-MM-dd") {
            val pos = listFavoriteMatch.indexOf(it)
            startActivity(
                intentFor<DetailMatchActivity>(
                    ParameterClass.ID_EVENT_MATCH_SELECTED to it.eventId,
                    ParameterClass.ID_SELECTED_LEAGUE_KEY to matchFavoriteOrig[pos].idLeague,
                    ParameterClass.NAME_LEAGUE_KEY to matchFavoriteOrig[pos].leagueName,
                    ParameterClass.LIST_LEAGUE_DATA to leagues
                ).clearTask().clearTop()
            )
        }
        recyclerView.adapter = recyclerAdapter
        presenter = FragmentMatchPresenter(requireContext(), this)
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

    override fun onLoaded(listMatch: MutableList<MatchLeagueData>, listFavorite: List<MatchFavoriteData>) {
        listFavoriteMatch.clear()
        matchFavoriteOrig.clear()
        matchFavoriteOrig.addAll(listFavorite)
        listFavoriteMatch.addAll(listMatch)
        recyclerAdapter.notifyDataSetChanged()
        if (listFavoriteMatch.isEmpty()) {
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
        val result = SpannableStringBuilder("Wahh... nampakya kamu belum suka match apapun :(")
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
        fun newInstance(leagues: LeagueResponse): MatchFavoriteFragment {
            val fragment = MatchFavoriteFragment()
            val extras = Bundle()
            extras.putSerializable(ParameterClass.LIST_LEAGUE_DATA, leagues)
            fragment.arguments = extras
            return fragment
        }
    }
}