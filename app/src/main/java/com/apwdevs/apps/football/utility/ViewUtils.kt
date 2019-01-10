package com.apwdevs.apps.football.utility

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return when (visibility) {
        View.VISIBLE -> true
        else -> false
    }
}

fun View.isGone(): Boolean {
    return when (visibility) {
        View.GONE -> true
        else -> false
    }

}