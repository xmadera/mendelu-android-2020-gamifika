package com.gamification.marketguards.data.model.missionsAndQuests

//@Entity(tableName = "quest_skill_preview")
data class QuestSkillPreview(
    val bonusExperiences: Int,
    val code: String,
    val experiences: Int,
    val id: Int,
    val questSubQuestSkills: MutableList<QuestSkillSubskillPreview>
)
