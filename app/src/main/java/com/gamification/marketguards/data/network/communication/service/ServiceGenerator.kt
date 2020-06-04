package com.gamification.marketguards.data.network.communication.service


import android.content.Context
import com.gamification.marketguards.data.network.SessionManagerInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        @Volatile private var INSTANCE: ServiceGenerator? = null
        private var context: Context? = null
        private var sessionManager: SessionManagerInterface? = null

        fun getInstance(context: Context, sessionManager: SessionManagerInterface): ServiceGenerator {
            if (Companion.context == null) {
                Companion.context = context
            }

            if (Companion.sessionManager == null) {
                Companion.sessionManager = sessionManager
            }

            return INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: ServiceGenerator()
            }
        }
    }

    fun <S> create(serviceClass:  Class<S>): S {
        val requestInterceptor = Interceptor { chain ->
            val token = sessionManager?.fetchAuthToken()

            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", token ?: "")
                .addHeader("Content-Type", "application/json")
                .build()

            return@Interceptor chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(
                ConnectivityInterceptor(
                    context!!
                )
            )
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:8080/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }

}