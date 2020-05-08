package com.gamification.marketguards.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "quests", foreignKeys = arrayOf(
//    ForeignKey(entity = Mission::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("missionId"),
//        onDelete = ForeignKey.CASCADE)
//))

class Quest(
    @ColumnInfo(name = "title")
    var title: String) {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "story")
    var story: String? = null

    @ColumnInfo(name = "missionId")
    var missionId: Long? = null

}