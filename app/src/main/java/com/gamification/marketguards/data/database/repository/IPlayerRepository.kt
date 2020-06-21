package com.gamification.marketguards.data.database.repository

import com.gamification.marketguards.data.model.player.GameStatus

interface IPlayerRepository {
    suspend fun getGameStatus(): GameStatus
}