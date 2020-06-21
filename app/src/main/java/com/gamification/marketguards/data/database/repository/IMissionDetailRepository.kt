package com.gamification.marketguards.data.database.repository

import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail

interface IMissionDetailRepository {
    suspend fun findById(id: Int): MissionDetail
    suspend fun getAllQuests(): MissionDetail
}