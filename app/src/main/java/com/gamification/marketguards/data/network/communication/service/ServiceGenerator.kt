package com.gamification.marketguards.data.network.communication.service


import android.content.Context
import com.gamification.marketguards.data.network.communication.service.session.SessionManagerInterface
import com.gamification.marketguards.data.network.communication.service.session.TokenRefreshAuthenticator
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {
        @Volatile
        private var INSTANCE: ServiceGenerator? = null
        private var context: Context? = null
        private var sessionManager: SessionManagerInterface? = null

        fun getInstance(
            context: Context,
            sessionManager: SessionManagerInterface
        ): ServiceGenerator {
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

    fun <S> create(serviceClass: Class<S>): S {
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
            .authenticator(
                TokenRefreshAuthenticator(
                    context!!,
                    sessionManager!!
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://37.46.208.113:8080/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }

    fun <S> createForRefreshToken(serviceClass: Class<S>, token: String): S {
        val requestInterceptor = Interceptor { chain ->

            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", token)
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
            .authenticator(
                TokenRefreshAuthenticator(
                    context!!,
                    sessionManager!!
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://37.46.208.113:8080/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}