package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites

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
import com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.matchsFavorites.MatchFavoriteFragment
import com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites.teamsFavorites.TeamFavoriteFragment
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {
    private lateinit var viewPagerHolder: ViewPager
    private lateinit var fragmentAdapter: FragmentStateFavoriteAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var leagues: LeagueResponse

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabLayout = fragment_favorite_id_tabLayout
        leagues = arguments?.getSerializable(ParameterClass.LIST_LEAGUE_DATA) as LeagueResponse
        viewPagerHolder = fragment_favorite_id_viewpagerholder
        fragmentAdapter = FragmentStateFavoriteAdapter(fragmentManager!!)
        viewPagerHolder.adapter = fragmentAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPagerHolder.setCurrentItem(tab?.position ?: 0, true)
            }

        })
        viewPagerHolder.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        viewPagerHolder.setCurrentItem(0, true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_favorites, container, false)
    }

    inner class FragmentStateFavoriteAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val fragments = arrayListOf<Fragment>(
            MatchFavoriteFragment.newInstance(leagues = leagues),
            TeamFavoriteFragment.newInstance(leagues = leagues)
            //FragmentLastMatch.newInstance(leagues = leagues)
        )

        override fun getItem(p0: Int): Fragment = fragments[p0]

        override fun getCount(): Int = 2

    }

    companion object {
        fun newInstance(leagues: List<TeamLeagueData>): FavoritesFragment {
            val fragment = FavoritesFragment()
            val args = Bundle()
            args.putSerializable(ParameterClass.LIST_LEAGUE_DATA, LeagueResponse(leagues))
            fragment.arguments = args
            return fragment
        }
    }
}