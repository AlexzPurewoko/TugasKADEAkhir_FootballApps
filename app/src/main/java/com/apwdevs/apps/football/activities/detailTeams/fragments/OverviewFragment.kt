package com.apwdevs.apps.football.activities.detailTeams.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.apwdevs.apps.football.utility.ParameterClass
import org.jetbrains.anko.*

class OverviewFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var textView: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val text = arguments?.getString(ParameterClass.KEY_ARGUMENT_DESCRIPTION)
        textView.text = text
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        scrollView {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            textView = textView { }.lparams(width = matchParent, height = wrapContent)
        }
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