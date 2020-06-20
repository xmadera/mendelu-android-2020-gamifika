package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.Entity

@Entity(tableName = "quest_subskill_preview")
data class QuestSkillSubskillPreview(
    val bonusExperiences: Int,
    val code: String,
    val experiences: Int,
    val id: Int
)
