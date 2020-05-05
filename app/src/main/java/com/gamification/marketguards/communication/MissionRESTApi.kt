package com.gamification.marketguards.communication

import com.gamification.marketguards.model.Mission
import retrofit2.Response
import retrofit2.http.GET


interface MissionRESTApi {

    @GET("missions")
    suspend fun getTasks(): Response<MutableList<Mission>>
}