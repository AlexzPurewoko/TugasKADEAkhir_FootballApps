package com.apwdevs.apps.football.activities.home.homeUtility

import android.content.Context
import android.support.v7.widget.SearchView
import android.util.AttributeSet

class CustomSearchView : SearchView {

    var onSearchCallback: CustomSearchViewCallback? = null

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

    override fun onActionViewCollapsed() {
        super.onActionViewCollapsed()
        onSearchCallback?.onActionViewCollapsed()
    }

    override fun onActionViewExpanded() {
        super.onActionViewExpanded()
        onSearchCallback?.onActionViewExpanded()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onSearchCallback?.onDetachedFromWindow()
    }

    interface CustomSearchViewCallback {
        fun onActionViewCollapsed()
        fun onActionViewExpanded()
        fun onDetachedFromWindow()
    }
}