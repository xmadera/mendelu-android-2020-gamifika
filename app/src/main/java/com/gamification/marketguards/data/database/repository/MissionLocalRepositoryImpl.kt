package com.gamification.marketguards.data.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.database.MissionsDatabase
import com.gamification.marketguards.data.database.dao.MissionsDao
import com.gamification.marketguards.data.model.Mission

class MissionLocalRepositoryImpl(private val context: Context) :
    IMissionRepository {

    private val missionsDao: MissionsDao = MissionsDatabase.getDatabase(context).missionsDao()

    private var getAllLiveData: LiveData<MutableList<Mission>> = missionsDao.getAll()

    override fun getAll(): LiveData<MutableList<Mission>> {
        return getAllLiveData
    }

    override suspend fun findById(id: Long): Mission {
        return missionsDao.findById(id)
    }

    override suspend fun insert(mission: Mission): Long {
        return missionsDao.insert(mission)
    }

    override suspend fun update(mission: Mission) {
        missionsDao.update(mission)
    }

    override suspend fun delete(mission: Mission) {
        missionsDao.delete(mission)
    }

}