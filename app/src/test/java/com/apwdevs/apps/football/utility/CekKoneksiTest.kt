package com.apwdevs.apps.football.utility

import android.content.Context
import com.apwdevs.apps.football.BuildConfig
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class CekKoneksiTest {

    @Mock
    private lateinit var ctx: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun isConnected() {
        val cekKoneksi = mock(CekKoneksi::class.java)
        val cProvider = TestCoroutineContextProvider()
        runBlocking(cProvider.main) {
            cekKoneksi.isConnected(ctx, coroutineContextProvider = cProvider)
            Mockito.verify(cekKoneksi).isConnected(ctx, coroutineContextProvider = cProvider)
        }
    }

    @Test
    fun isReachableNetworks() {
        val cekKoneksi = mock(CekKoneksi::class.java)
        val cProvider = TestCoroutineContextProvider()
        runBlocking(cProvider.main) {
            val result = cekKoneksi.isReachableNetworks(BuildConfig.BASE_URL, coroutineContextProvider = cProvider)
            Mockito.verify(cekKoneksi).isReachableNetworks(BuildConfig.BASE_URL, coroutineContextProvider = cProvider)
        }
    }
}