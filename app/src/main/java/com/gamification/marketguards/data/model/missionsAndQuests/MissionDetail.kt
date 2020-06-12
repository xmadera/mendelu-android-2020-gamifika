package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "mission_detail")
data class MissionDetail(
    val id:	Int,
    val title: String,
    val storyFinish: String,
    val story:	String,
    val firstSeen: Boolean,
    val finishedOptionalQuests: Int,
    val preparedQuests: MutableList<QuestPreview>,
    val finishedQuests: MutableList<QuestPreview>,
    val activeQuests: MutableList<QuestPreview>,
    val questSkillDtos: MutableList<SkillPreview>,
    val titleImage: String
)