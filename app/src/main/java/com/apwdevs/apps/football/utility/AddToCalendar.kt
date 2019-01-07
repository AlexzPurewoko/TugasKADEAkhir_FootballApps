package com.apwdevs.apps.football.utility

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import java.util.*

object AddToCalendar {
    fun add(
        ctx: Context,
        title: String,
        description: String,
        timeEvent: String,
        dateEvent: String
    ) {


        val calendarNow = MyDate.getCalendarInGMT7(timeEvent, dateEvent, "dd/MM/yyyy")

        val yearsNow = calendarNow.get(Calendar.YEAR) + 2000
        val monthNow = calendarNow.get(Calendar.MONTH)
        val dateNow = calendarNow.get(Calendar.DAY_OF_MONTH)

        val hours = calendarNow.get(Calendar.HOUR_OF_DAY)
        val minutes = calendarNow.get(Calendar.MINUTE)

        val beginTime = Calendar.getInstance()
        beginTime.set(yearsNow, monthNow, dateNow, hours, minutes)

        val endTime = Calendar.getInstance()
        endTime.set(yearsNow, monthNow, dateNow, hours + 1, minutes)


        val intent = Intent(Intent.ACTION_INSERT)
        intent.data = CalendarContract.Events.CONTENT_URI
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
        intent.putExtra(CalendarContract.Events.TITLE, title)
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE)
        ctx.startActivity(intent)
    }
}