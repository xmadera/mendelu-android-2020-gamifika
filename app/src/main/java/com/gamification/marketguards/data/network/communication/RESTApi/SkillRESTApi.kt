package com.gamification.marketguards.data.network.communication.RESTApi

import com.gamification.marketguards.data.model.skills.SkillPreview
import retrofit2.Response
import retrofit2.http.GET


interface SkillRESTApi {

    @GET("skills")
    suspend fun getSkills(): Response<MutableList<SkillPreview>>
}