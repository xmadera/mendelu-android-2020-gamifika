package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "quest_subskill_preview")
data class QuestSkillSubskillPreview(
    val bonusExperiences: Int,
    val code: String,
    val experiences: Int,
    val id: Int
)
