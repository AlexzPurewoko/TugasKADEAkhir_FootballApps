package com.apwdevs.apps.football.activities.home.fragments.fragmentMatch

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.lastMatch.FragmentLastMatch
import com.apwdevs.apps.football.activities.home.fragments.fragmentMatch.nextMatch.FragmentNextMatch
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass

class MatchFragments : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewpagerContainer: ViewPager
    private lateinit var leagues: LeagueResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_matchs, container, false)
        tabLayout = layout.findViewById(R.id.fragment_match_id_tabLayout)
        viewpagerContainer = layout.findViewById(R.id.fragment_match_id_viewpagerholder)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        leagues = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        viewpagerContainer.adapter = FragmentStateMatchAdapter(fragmentManager!!)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpagerContainer.setCurrentItem(tab?.position ?: 0, true)
            }

        })
        viewpagerContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                tabLayout.setScrollPosition(p0, p1, false)

            }

            override fun onPageSelected(p0: Int) {
                val tabs = tabLayout.getTabAt(p0)
                tabs?.select()
            }

        })

    }

    inner class FragmentStateMatchAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val fragments = arrayListOf(
            FragmentNextMatch.newInstance(leagues = leagues),
            FragmentLastMatch.newInstance(leagues = leagues)
        )

        override fun getItem(p0: Int): Fragment = fragments[p0]

        override fun getCount(): Int = 2

    }

    companion object {
        fun newInstance(leagues: List<TeamLeagueData>): MatchFragments {
            val fragment = MatchFragments()
            val args = Bundle()
            args.putSerializable(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(leagues))
            fragment.arguments = args
            return fragment
        }
    }
}

