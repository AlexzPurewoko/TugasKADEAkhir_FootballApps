package com.apwdevs.apps.football.utility

import android.util.Log
import com.apwdevs.apps.football.BuildConfig
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object AvailableDataUpdates {
    private const val maxTookServerInMillis = 15000 // 15s
    private const val maxCountUserRefresh = 2
    private const val maxPercentAvailable = 40
    fun isAvailable(
        cacheFiles: List<File>,
        isTesting: Boolean,
        countUserRefresh: Int,
        contextPool: CoroutineContextProvider = CoroutineContextProvider()
    ): Deferred<ConnectionResult> = GlobalScope.async(contextPool.main) {
        var isFinishedCheckConnection = false
        var isConnected = false
        var tookServerMilis: Long = 0
        val msg: String

        if (isTesting) {
            msg = ParameterClass.MSG_IN_TESTING_MODE
            return@async ConnectionResult(msg, ResultConnection.IN_TESTING_MODE, false)
        }

        var cacheCondition = true
        for (cache in cacheFiles) {
            cacheCondition = cacheCondition && cache.exists()
        }
        val refreshIsMax = countUserRefresh >= maxCountUserRefresh
        GlobalScope.doAsync {
            val startConnect = System.currentTimeMillis()
            try {
                val url = URL(BuildConfig.BASE_URL)
                val httpURL = url.openConnection() as HttpURLConnection
                httpURL.setRequestProperty("User-Agent", "test")
                httpURL.setRequestProperty("Connection", "close")
                httpURL.connectTimeout = 20000
                httpURL.connect()
                isConnected = httpURL.responseCode == HttpURLConnection.HTTP_OK
            } catch (e: IOException) {
                if (!isTesting)
                    Log.e(javaClass.simpleName, "Error when establishing connection, e: ${e.localizedMessage}")
            } finally {
                val endConnect = System.currentTimeMillis()
                tookServerMilis = endConnect - startConnect
                isFinishedCheckConnection = true
            }
        }
        while (!isFinishedCheckConnection) {
            if (isTesting)
                Thread.sleep(1000)
            else
                delay(1000)
        }

        val internetPercent =
            if (isConnected) (maxTookServerInMillis - tookServerMilis) * 100 / maxTookServerInMillis else 0
        val preventToUpdate: Boolean
        val enumResult: ResultConnection
        /*if (isTesting) {
            when {
                isConnected -> {
                    msg = "Missing cache files, so we need to update data from server"
                    enumResult = ResultConnection.ERR_CACHE_IS_MISSING
                    preventToUpdate = true
                }
                else -> {
                    preventToUpdate = false
                    enumResult = ResultConnection.ERR_CACHE_AND_CONNECTION_NOT_AVAIL
                    msg =
                            "Sorry we have lost anything, internet is slowly $internetPercent%, so please enable your right connection to update data from server"
                }
            }
        } else {*/
            when {
                refreshIsMax && isConnected && internetPercent > maxPercentAvailable -> {
                    enumResult = ResultConnection.REQ_UPDATE
                    msg = "In my opinion, you have to update data from server right?, internet about $internetPercent%"
                    preventToUpdate = true
                }
                isConnected -> {
                    enumResult = ResultConnection.ERR_CACHE_IS_MISSING
                    msg = "Missing cache files, so we need to update data from server, internet about $internetPercent%"
                    preventToUpdate = true
                }
                else -> {
                    preventToUpdate = false
                    enumResult = ResultConnection.ERR_CACHE_AND_CONNECTION_NOT_AVAIL
                    msg = ":(\nSorry we have lost anything, internet is slowly $internetPercent%"
                }
            }

        //}
        ConnectionResult(msg, enumResult, preventToUpdate)
    }


}

enum class TestingType {
    TEST_TYPE_INSTRUMENTATION,
    TEST_TYPE_UNIT
}

enum class ResultConnection {
    ERR_CACHE_IS_MISSING,
    ERR_CACHE_AND_CONNECTION_NOT_AVAIL,
    CACHE_IS_AVAIL,
    REQ_UPDATE,
    IN_TESTING_MODE
}

data class ConnectionResult(
    val msg: String?,
    val enumResult: ResultConnection,
    val preventToUpdate: Boolean
)