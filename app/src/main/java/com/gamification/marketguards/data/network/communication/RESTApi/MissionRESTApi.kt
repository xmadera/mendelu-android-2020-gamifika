package com.gamification.marketguards.data.network.communication.RESTApi

import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query


interface MissionRESTApi {

    @GET("missions/preview")
    suspend fun getMissionsPreview(): Response<MutableList<MissionPreview>>

    @PATCH("missions/detail")
    suspend fun getMissionDetail(@Query("missionId") id: Int): MissionDetail

    @PATCH("missions/detail")
    suspend fun getAllQuests(): MissionDetail

}