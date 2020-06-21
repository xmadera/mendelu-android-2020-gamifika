package com.gamification.marketguards.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gamification.marketguards.data.database.dao.MissionsDao
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview

@Database(entities = [MissionPreview::class], version = 4, exportSchema = true)
abstract class MissionsDatabase : RoomDatabase() {

    abstract fun missionsDao(): MissionsDao

    companion object {

        private var INSTANCE: MissionsDatabase? = null

        fun getDatabase(context: Context): MissionsDatabase {
            if (INSTANCE == null) {
                synchronized(MissionsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MissionsDatabase::class.java, "missions_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}