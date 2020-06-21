package com.gamification.marketguards.data.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.database.MissionsDatabase
import com.gamification.marketguards.data.database.dao.MissionsDao
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview

class MissionsLocalDetailRepositoryImpl(context: Context) :
    IMissionPreviewRepository {

    private val missionsPreviewDao: MissionsDao =
        MissionsDatabase.getDatabase(context).missionsDao()

    private var getAllLiveData: LiveData<MutableList<MissionPreview>> = missionsPreviewDao.getAll()

    override fun getAll(): LiveData<MutableList<MissionPreview>> {
        return getAllLiveData
    }

    override suspend fun insert(mission: MissionPreview): Long {
        return missionsPreviewDao.insert(mission)
    }

}