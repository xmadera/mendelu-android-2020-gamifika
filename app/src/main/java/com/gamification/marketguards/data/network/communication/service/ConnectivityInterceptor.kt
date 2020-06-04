package com.gamification.marketguards.data.network.communication.service

import android.content.Context
import android.net.ConnectivityManager
import com.gamification.marketguards.data.exceptions.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            throw NoConnectionException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork != null
    }
}