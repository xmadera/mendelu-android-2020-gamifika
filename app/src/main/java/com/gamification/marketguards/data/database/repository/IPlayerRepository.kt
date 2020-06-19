package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.model.player.GameStatus

interface IPlayerRepository {
    suspend fun getGameStatus(): GameStatus
}