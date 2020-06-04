package com.gamification.marketguards.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.network.communication.MissionRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class DashBoardViewModel(private val missionRepository: MissionRESTApiRepositoryImpl) : ViewModel(), CoroutineScope by MainScope() {

    fun getAll(): LiveData<MutableList<MissionPreview>> {
        return missionRepository.getAll()
    }

    suspend fun findById(id: Int) : MissionDetail {
        return missionRepository.findById(id)
    }

    override fun onCleared() {
        super.onCleared()
    }

}