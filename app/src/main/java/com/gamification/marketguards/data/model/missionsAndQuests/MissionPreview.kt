package com.gamification.marketguards.data.model.missionsAndQuests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mission_preview")
data class MissionPreview(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "dateCreated")
    var dateCreated: String?,

    @ColumnInfo(name = "dateFinished")
    var dateFinished: String?,

    @ColumnInfo(name = "questsSeen")
    var questsSeen: Boolean?,

    @ColumnInfo(name = "firstSeen")
    var firstSeen: Boolean?,

    @ColumnInfo(name = "finishedOptionalQuests")
    var finishedOptionalQuests: Int?,

    @ColumnInfo(name = "finishedQuests")
    var finishedQuests: Int?,

    @ColumnInfo(name = "totalOptionalQuests")
    var totalOptionalQuests: Int?,

    @ColumnInfo(name = "totalQuests")
    var totalQuests: Int?
)
