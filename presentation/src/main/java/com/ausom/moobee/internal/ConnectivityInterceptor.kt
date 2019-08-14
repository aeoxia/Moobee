package com.ausom.moobee.internal

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.ausom.domain.common.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(
    app: Application
) : Interceptor {

    private val appContext = app.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


}