package com.gamification.marketguards.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gamification.marketguards.model.Mission

@Dao
interface MissionsDao {

    @Query("SELECT * FROM missions")
    fun getAll(): LiveData<MutableList<Mission>>

    @Query("SELECT * FROM missions WHERE id = :id")
    suspend fun findById(id : Long): Mission

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mission: Mission): Long

    @Update
    suspend fun update(mission: Mission)

    @Delete
    suspend fun delete(mission: Mission)
}