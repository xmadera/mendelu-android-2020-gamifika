package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview

interface IMissionPreviewRepository {
    fun getAll(): LiveData<MutableList<MissionPreview>>
    suspend fun insert(mission: MissionPreview): Long
}