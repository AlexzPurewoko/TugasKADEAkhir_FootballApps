package com.apwdevs.apps.football.utility

import android.content.Context
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object LoadImage {
    fun load(ctx: Context, url: String?, isTesting: Boolean = false): RequestCreator? {
        return if (isTesting) {
            val resId = getId(url, ctx)
            if (resId != 0)
                Picasso.get().load(resId)
            else null
        } else {
            Picasso.get().load(url)
        }
    }

    fun getId(url: String?, ctx: Context): Int {
        if (url.isNullOrEmpty()) return 0
        val resId = StringBuffer()
        var len = url.length - 1
        var afterDots = false
        while (len > -1) {
            val chr = url[len]
            if (chr == '/') break
            if (chr == '.') {
                afterDots = true
            } else if (afterDots)
                resId.append(chr)
            len--
        }
        resId.reverse()
        if (resId[0].isDigit()) {
            resId.insert(0, 'r')
        }
        val id = ctx.resources.getIdentifier(
            resId.toString(),
            "drawable",
            "com.apwdevs.apps.football"
        )
        return id
    }
}