package com.gamification.marketguards.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview

@Dao
interface MissionsDao {

    @Query("SELECT * FROM mission_preview")
    fun getAll(): LiveData<MutableList<MissionPreview>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mission: MissionPreview): Long
}