package com.gamification.marketguards.data.network.communication

import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.model.skills.SkillPreview
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*
import java.util.HashMap


interface SkillRESTApi {

    @GET("skills")
    suspend fun getSkills(): Response<MutableList<SkillPreview>>
}