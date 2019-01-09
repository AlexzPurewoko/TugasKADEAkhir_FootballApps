package com.apwdevs.apps.football.activities.home.homeUtility

import android.widget.SearchView

interface FragmentHomeCallback : SearchView.OnQueryTextListener, CustomSearchView.CustomSearchViewCallback {
    fun transactionData(what: String)
}