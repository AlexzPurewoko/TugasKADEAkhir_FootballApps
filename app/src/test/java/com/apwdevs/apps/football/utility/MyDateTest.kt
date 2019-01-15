package com.apwdevs.apps.football.utility

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class MyDateTest {

    @Test
    fun getDateFromCalendar() {
        val date = "06-12-2018"
        val expectedValues = "Selasa, 6 Dec 2018"
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(date)
        val execute = MyDate.getDateFromCalendar(calendar)
        Assert.assertEquals(expectedValues, execute)
    }

    @Test
    fun getTimeFromCalendar() {
        val time = "09:39:00"
        val expectedValues = "09:39"
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("HH:mmm:ss", Locale.getDefault()).parse(time)
        val execute = MyDate.getTimeFromCalendar(calendar)
        Assert.assertEquals(expectedValues, execute)
    }

    @Test
    fun getCalendarInGMT7() {
        val strTimeGMT0 = "05:30:00"
        val strDateGMT0 = "19-05-2018"

        val resultInGMT7 = "12:30"
        val dateResult = "Sabtu, 19 May 2018"

        val execute = MyDate.getCalendarInGMT7(strTimeGMT0, strDateGMT0, "dd-MM-yyyy")
        val returnedTime = MyDate.getTimeFromCalendar(execute)
        val returnedDate = MyDate.getDateFromCalendar(execute)

        Assert.assertEquals(resultInGMT7, returnedTime)
        Assert.assertEquals(dateResult, returnedDate)
    }
}