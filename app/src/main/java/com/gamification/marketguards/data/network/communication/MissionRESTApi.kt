package com.gamification.marketguards.data.network.communication

import com.gamification.marketguards.data.model.Mission
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MissionRESTApi {

    @GET("missions")
    suspend fun getMissions(): Response<MutableList<Mission>>

    @GET("mission/{id}")
    suspend fun getMission(@Path("id") id : Long): Response<Mission>

}