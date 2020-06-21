package com.gamification.marketguards.data.database.repository

import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail

interface IQuestRepository {
    suspend fun findById(id: Int): QuestDetail
}