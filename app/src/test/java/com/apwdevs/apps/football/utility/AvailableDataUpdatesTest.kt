package com.apwdevs.apps.football.utility

import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.io.File

class AvailableDataUpdatesTest {

    @Test
    fun isAvailable() {
        val countUserRefresh = 2
        val listfiles = mutableListOf(File("testing"))
        val availUpdates = mock(AvailableDataUpdates::class.java)
        val contextProvider = TestCoroutineContextProvider()
        availUpdates.isAvailable(listfiles, true, countUserRefresh, contextPool = contextProvider)
        Mockito.verify(availUpdates).isAvailable(listfiles, true, countUserRefresh, contextPool = contextProvider)
    }
}