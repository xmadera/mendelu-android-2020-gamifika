package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "quests", foreignKeys = arrayOf(
//    ForeignKey(entity = Mission::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("missionId"),
//        onDelete = ForeignKey.CASCADE)
//))

@Entity(tableName = "quest_preview")
data class QuestPreview(
    val activated: Boolean,
    val bonusExperiences: Int,
    val created: String,
    val currency: Int,
    val difficultyEvaluation: Int,
    val experiences: Int,
    val finished: Boolean,
    val id: Int,
    val missionTitle: String,
    val questSkillDtos: MutableList<SkillPreview>,
    val seen: Boolean,
    val story: String,
    val timeToActivate: Int,
    val timeToFinish: Int,
    val title: String
)