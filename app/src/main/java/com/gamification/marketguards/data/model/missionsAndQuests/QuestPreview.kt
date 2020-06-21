package com.gamification.marketguards.data.model.missionsAndQuests

//@Entity(tableName = "quest_preview")
data class QuestPreview(
    val activated: String?,
    val bonusExperiences: Int,
    val created: String,
    val currency: Int,
    val difficultyEvaluation: Int?,
    val experiences: Int,
    val finished: String?,
    val id: Int,
    val missionTitle: String,
    val questSkillDtos: MutableList<QuestSkillPreview>,
    val seen: Boolean,
    val story: String,
    val timeToActivate: Int?,
    val timeToFinish: Int?,
    val title: String
)