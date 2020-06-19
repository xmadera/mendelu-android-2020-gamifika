package com.gamification.marketguards.data.network.communication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.database.repository.IMissionRepository
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MissionRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
): IMissionRepository,  CoroutineScope by MainScope() {

    private val missionsApi: MissionRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(MissionRESTApi::class.java)

    private val missionPreviewLiveData = MutableLiveData<MutableList<MissionPreview>>()

    override fun getAll(): LiveData<MutableList<MissionPreview>> {
        launch {
            val response = missionsApi.getMissionsPreview()
            if (response.isSuccessful) {
                missionPreviewLiveData.postValue(response.body())
            }
        }
        return missionPreviewLiveData
    }

    override suspend fun findById(id: Int): MissionDetail {
        return missionsApi.getMissionDetail(id)
    }

    override suspend fun getAllQuests(): MissionDetail {
        return missionsApi.getAllQuests()
    }
}