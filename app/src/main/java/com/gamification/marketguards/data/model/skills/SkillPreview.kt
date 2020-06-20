package com.gamification.marketguards.data.model.skills

import androidx.room.Entity

@Entity(tableName = "skill_preview")
data class SkillPreview(
    val experiencesToNextLevel: Int,
    val code: String,
    val experiences: Int,
    val id: Int,
    val level: Int,
//    val subSkills: [],
    val title: String
)
