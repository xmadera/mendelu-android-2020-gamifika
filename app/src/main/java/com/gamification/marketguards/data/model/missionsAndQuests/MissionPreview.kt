package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.Entity

@Entity(tableName = "mission_preview")
data class MissionPreview(
    val id: Int,
    val title: String,
    val dateCreated: String,
    val dateFinished: String,
    val questsSeen: Boolean,
    val firstSeen: Boolean,
    val finishedOptionalQuests: Int,
    val finishedQuests: Int,
    val totalOptionalQuests: Int,
    val totalQuests: Int
)
