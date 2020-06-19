package com.gamification.marketguards.data.model.player

import androidx.room.Entity

@Entity(tableName = "game_status")
data class GameStatus(
    val level: Int,
    val currency: Int,
    val experiences: Int,
    val experiencesRangeFrom: Int,
    val experiencesRangeTo: Int,
    val analysis: Int,
    val contacts: Int,
    val addressing: Int,
    val consulting: Int,
    val services: Int
)