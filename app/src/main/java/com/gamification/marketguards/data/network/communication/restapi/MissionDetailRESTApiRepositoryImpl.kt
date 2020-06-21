package com.gamification.marketguards.data.network.communication.restapi

import android.content.Context
import com.gamification.marketguards.data.database.repository.IMissionDetailRepository
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.gamification.marketguards.data.network.communication.service.session.SessionManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MissionDetailRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
) : IMissionDetailRepository, CoroutineScope by MainScope() {

    private val missionsApi: MissionRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(MissionRESTApi::class.java)

    override suspend fun findById(id: Int): MissionDetail {
        return missionsApi.getMissionDetail(id)
    }

    override suspend fun getAllQuests(): MissionDetail {
        return missionsApi.getAllQuests()
    }
}