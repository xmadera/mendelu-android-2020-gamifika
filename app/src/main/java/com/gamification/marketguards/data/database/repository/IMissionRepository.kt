package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview

interface IMissionRepository {
    fun getAll(): LiveData<MutableList<MissionPreview>>
    suspend fun findById(id : Int): MissionDetail
    suspend fun getAllQuests(): MissionDetail
}