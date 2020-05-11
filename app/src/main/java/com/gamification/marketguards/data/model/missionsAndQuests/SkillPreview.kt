package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "skill_preview")
data class SkillPreview(
    val bonusExperiences: Int,
    val code: String,
    val experiences: Int,
    val id: Int,
    val questSubSkills: MutableList<SkillSubskillPreview>
)
