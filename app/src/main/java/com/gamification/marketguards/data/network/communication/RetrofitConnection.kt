package com.gamification.marketguards.data.network.communication

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitConnection {

    companion object {
        fun getRetrofit(): Retrofit {
            val builder: Retrofit.Builder = Retrofit.Builder()
            builder
                .baseUrl("https://gamifika-missions.free.beeceptor.com")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            return builder.build()
        }
    }
}