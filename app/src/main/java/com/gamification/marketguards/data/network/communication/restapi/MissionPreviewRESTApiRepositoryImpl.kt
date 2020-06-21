package com.gamification.marketguards.data.network.communication.restapi

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.database.repository.IMissionPreviewRepository
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.gamification.marketguards.data.network.communication.service.session.SessionManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MissionPreviewRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
) : IMissionPreviewRepository, CoroutineScope by MainScope() {

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

    override suspend fun insert(mission: MissionPreview): Long {
        return mission.id!!.toLong()
    }
}