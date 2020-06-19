package com.gamification.marketguards.data.network.communication

import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.http.*
import java.util.HashMap


interface QuestRESTApi {

    @GET("quests/{id}")
    suspend fun getQuestDetail(@Path("id") id : Int): QuestDetail

    @PATCH("quests/{id}/activate")
    suspend fun startQuest(@Path("id") id : Int)

    @PATCH("quests/{id}/finish")
    suspend fun finishQuest(@Path("id") id : Int)

    @PATCH("quests/{id}")
    suspend fun editQuestNotes(@Path("id") id : Int, @Body note : JsonObject)
}