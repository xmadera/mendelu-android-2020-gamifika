package com.gamification.marketguards.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(tableName = "quests", foreignKeys = arrayOf(
//    ForeignKey(entity = Mission::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("missionId"),
//        onDelete = ForeignKey.CASCADE)
//))

class Quest(
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "story")
    var story: String = "") {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "taskId")
    var taskId: Long? = null

}