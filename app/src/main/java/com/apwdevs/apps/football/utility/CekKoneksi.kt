package com.apwdevs.apps.football.utility

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.InetAddress

object CekKoneksi {
    @Throws(InterruptedException::class)
    fun isConnected(
        activity: Context,
        host: String = "8.8.8.8",
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
        try {
            val inetAddress = InetAddress.getByName(host)
            inetAddress.hostAddress != null && inetAddress.hostAddress.length > 1
        } catch (e: IOException) {
            false
        }


    }
}