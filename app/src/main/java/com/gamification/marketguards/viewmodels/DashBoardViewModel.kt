package com.gamification.marketguards.viewmodels

import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.network.communication.restapi.MissionDetailRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class DashBoardViewModel(private val missionPreviewRepository: MissionDetailRESTApiRepositoryImpl) :
    ViewModel(), CoroutineScope by MainScope() {


    suspend fun findById(id: Int): MissionDetail {
        return missionPreviewRepository.findById(id)
    }

    suspend fun getAllQuests(): MissionDetail {
        return missionPreviewRepository.getAllQuests()
    }
}