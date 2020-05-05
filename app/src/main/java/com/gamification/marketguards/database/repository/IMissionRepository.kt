package com.gamification.marketguards.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.model.Mission

interface IMissionRepository {
    fun getAll(): LiveData<MutableList<Mission>>
    suspend fun findById(id : Long): Mission
    suspend fun insert(mission: Mission): Long
    suspend fun update(mission: Mission)
    suspend fun delete(mission: Mission)
}