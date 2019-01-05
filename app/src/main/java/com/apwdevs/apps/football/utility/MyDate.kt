package com.apwdevs.apps.football.utility

import java.text.SimpleDateFormat
import java.util.*

object MyDate {
    fun getDate(strDate: String?, pattern: String): CharSequence? {
        if (strDate == null) return null
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat(pattern, Locale.getDefault()).parse(strDate)
        val day = getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK))
        val month = getNameOfMonth(calendar.get(Calendar.MONTH))
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)

        return "$day, $date $month $year"
    }

    fun getTimeInGMT7(strTime: String?, pattern: String = "HH:mm:ss"): CharSequence? {
        if (strTime == null) return null
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat(pattern, Locale.getDefault()).parse(strTime)
        return "${calendar.get(Calendar.HOUR) + 7}:${calendar.get(Calendar.MINUTE)}"
    }

    private fun getNameOfMonth(monthVal: Int): String = when (monthVal) {
        Calendar.JANUARY -> "Jan"
        Calendar.FEBRUARY -> "Feb"
        Calendar.MARCH -> "March"
        Calendar.APRIL -> "April"
        Calendar.MAY -> "May"
        Calendar.JUNE -> "June"
        Calendar.JULY -> "July"
        Calendar.AUGUST -> "August"
        Calendar.SEPTEMBER -> "Sep"
        Calendar.OCTOBER -> "Oct"
        Calendar.NOVEMBER -> "Nov"
        Calendar.DECEMBER -> "Dec"
        else -> "nan"
    }

    private fun getNameOfDay(dayId: Int): String = when (dayId) {
        Calendar.SUNDAY -> "Minggu"
        Calendar.MONDAY -> "Senin"
        Calendar.THURSDAY -> "Selasa"
        Calendar.WEDNESDAY -> "Rabu"
        Calendar.SATURDAY -> "Sabtu"
        Calendar.TUESDAY -> "Kamis"
        Calendar.FRIDAY -> "Jum'at"
        else -> "nan"
    }
}