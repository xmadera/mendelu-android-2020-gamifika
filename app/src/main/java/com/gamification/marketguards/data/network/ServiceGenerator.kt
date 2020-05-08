package com.gamification.marketguards.data.network


import android.content.Context
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
            if (this.context == null) {
                this.context = context
            }

            if (this.sessionManager == null) {
                this.sessionManager = sessionManager
            }

            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ServiceGenerator()
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
            .addInterceptor(ConnectivityInterceptor(context!!))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://37.46.208.113:8080/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }

    fun <S> createForLocalhost(serviceClass: Class<S>): S {
        val requestInterceptor = Interceptor { chain ->
            val sessionManager = SessionManager(context!!)
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", sessionManager.fetchAuthToken() ?: "")
                .build()

            return@Interceptor chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(ConnectivityInterceptor(context!!))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://192.168.1.24:8080/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }

    fun <S> createForHeroku(serviceClass: Class<S>): S {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(context!!))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://client-notifier.herokuapp.com")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}