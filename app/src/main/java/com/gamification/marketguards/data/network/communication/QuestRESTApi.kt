package com.gamification.marketguards.data.network.communication

import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query


interface QuestRESTApi {

    @GET("quests/{id}")
    suspend fun getQuestDetail(@Path("id") id : Int): QuestDetail

    @PATCH("quests/{id}/activate")
    suspend fun startQuest(@Path("id") id : Int)
}