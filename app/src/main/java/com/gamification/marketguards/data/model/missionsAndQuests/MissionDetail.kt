package com.gamification.marketguards.data.model.missionsAndQuests

//@Entity(tableName = "mission_detail")
data class MissionDetail(
    val id: Int?,
    val title: String?,
    val storyFinish: String?,
    val story: String?,
    val firstSeen: Boolean?,
//    val finishedOptionalQuests: Int,
    val preparedQuests: MutableList<QuestPreview>,
    val finishedQuests: MutableList<QuestPreview>,
    val activeQuests: MutableList<QuestPreview>,
//    val questSkillDtos: MutableList<SkillPreview>,
    val titleImage: String?
)