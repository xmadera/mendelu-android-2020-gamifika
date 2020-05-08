package com.gamification.marketguards.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gamification.marketguards.data.database.dao.MissionsDao
import com.gamification.marketguards.data.model.Mission

@Database(entities = [Mission::class], version = 1, exportSchema = true)
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
                        ).addMigrations(MIGRATION_1_2)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE missions ADD COLUMN done INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE missions ADD COLUMN date INTEGER")
            }
        }
    }
}