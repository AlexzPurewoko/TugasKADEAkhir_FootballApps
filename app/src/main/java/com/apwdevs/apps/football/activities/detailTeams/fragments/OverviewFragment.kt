package com.apwdevs.apps.football.activities.detailTeams.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.utility.ParameterClass

class OverviewFragment : Fragment() {

    private lateinit var textView: TextView
    private var isTesting: Boolean = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_overview_about_teams, container, false)
        textView = layout.findViewById(R.id.fragment_team_overview_text)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val text = arguments?.getString(ParameterClass.KEY_ARGUMENT_DESCRIPTION)
        textView.text = text
    }


    companion object {
        fun newInstance(description: String): OverviewFragment {
            val fragment = OverviewFragment()
            val b = Bundle()
            b.putString(ParameterClass.KEY_ARGUMENT_DESCRIPTION, description)
            fragment.arguments = b
            return fragment
        }
    }
}