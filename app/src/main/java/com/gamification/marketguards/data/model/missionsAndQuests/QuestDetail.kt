package com.gamification.marketguards.data.model.missionsAndQuests

//@Entity(tableName = "quest_detail")
data class QuestDetail(
    val activated: String?,
    val averageTime: Int,
    val bonusExperiences: Int,
    val code: String,
//    val completingType: {id: 1, code: "coTyRaPla", title: "Hráč"}
    val created: String,
    val currency: Int,
    val difficultyEvaluation: Int,
    val experiences: Int,
    val finished: String?,
    val id: Int,
    val missionTitle: String,
    val note: String?,
    val questSkills: List<QuestSkillPreview>,
    val seen: Boolean,
    val story: String,
    val storyFinish: String?,
    val timeToFinish: Int?,
    val title: String
)