package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail

interface IQuestRepository {
    suspend fun findById(id : Int): QuestDetail
}