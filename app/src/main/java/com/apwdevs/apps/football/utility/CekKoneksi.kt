package com.apwdevs.apps.football.utility

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import java.io.IOException
import java.net.InetAddress

object CekKoneksi {
    @Throws(InterruptedException::class)
    fun isConnected(
        activity: Context,
        host: String = "google.com",
        coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider()
    ): Deferred<Boolean> {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            return isReachableNetworks(host, coroutineContextProvider)
        }
        return GlobalScope.async(coroutineContextProvider.main) {
            false
        }
    }

    fun isReachableNetworks(
        host: String = "google.com",
        coroutineContextProvider: CoroutineContextProvider
    ): Deferred<Boolean> = GlobalScope.async(coroutineContextProvider.main) {

        var isConnect = false
        var isFinished = false
        doAsync {
            try {
                val inetAddress = InetAddress.getByName(host)
                isConnect = inetAddress.hostAddress != null && inetAddress.hostAddress.length > 1
            } catch (e: IOException) {

            }
            isFinished = true
        }
        while (!isFinished) delay(1000)
        isConnect
    }
}