package com.apwdevs.apps.football.activities.home.fragments.fragmentFavorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.splash.dataController.LeagueResponse
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.utility.ParameterClass

class FavoritesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_favorites, container, false)
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