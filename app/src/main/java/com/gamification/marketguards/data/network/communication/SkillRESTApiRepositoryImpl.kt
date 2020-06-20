package com.gamification.marketguards.data.network.communication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.database.repository.ISkillRepository
import com.gamification.marketguards.data.model.skills.SkillPreview
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SkillRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
): ISkillRepository,  CoroutineScope by MainScope() {

    private val questApi: SkillRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(SkillRESTApi::class.java)

    private val skillPreviewLiveData = MutableLiveData<MutableList<SkillPreview>>()

    override fun getSkills(): LiveData<MutableList<SkillPreview>> {
        launch {
            val response = questApi.getSkills()
            if (response.isSuccessful) {
                skillPreviewLiveData.postValue(response.body())
            }
        }
        return skillPreviewLiveData
    }
}