package com.gamification.marketguards.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "missions")
data class Mission(
    @ColumnInfo(name = "title")
    var title: String) {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long? = null


    @Ignore
    var quests: MutableList<Quest> = mutableListOf()

}