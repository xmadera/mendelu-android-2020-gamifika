package com.gamification.marketguards.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gamification.marketguards.data.database.repository.IMissionRepository
import com.gamification.marketguards.data.model.Mission
import com.gamification.marketguards.data.network.communication.MissionRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

open class DashBoardViewModel(private val app: Application) : AndroidViewModel(app), CoroutineScope by MainScope() {

    protected var missionRepository: IMissionRepository = MissionRESTApiRepositoryImpl(app)
    private val remoteLiveData = missionRepository.getAll()

    private val remoteGetAllObserver = object: Observer<MutableList<Mission>> {
        override fun onChanged(t: MutableList<Mission>?) {
            t?.let {
                for (mission in it) {
                    launch { missionRepository.insert(mission) }
                }
            }
        }
    }

    fun getAll(): LiveData<MutableList<Mission>> {
        return missionRepository.getAll()
    }

    suspend fun findById(id: Long): Mission {
        return missionRepository.findById(id)
    }

    override fun onCleared() {
        remoteLiveData.removeObserver(remoteGetAllObserver)
        super.onCleared()
    }

}