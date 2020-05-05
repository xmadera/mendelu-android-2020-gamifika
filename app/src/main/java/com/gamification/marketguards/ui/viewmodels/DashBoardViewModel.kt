package com.gamification.marketguards.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gamification.marketguards.communication.MissionRESTApiRepositoryImpl
import com.gamification.marketguards.database.repository.MissionLocalRepositoryImpl
import com.gamification.marketguards.model.Mission
import com.gamification.marketguards.ui.viewmodels.base.BaseMissionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DashBoardViewModel(private val app: Application) : BaseMissionViewModel(app), CoroutineScope by MainScope() {

    private val remoteRepository = MissionRESTApiRepositoryImpl(app)
    private val remoteLiveData = remoteRepository.getAll()

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
        remoteRepository.getAll().observeForever(remoteGetAllObserver)
        return missionRepository.getAll()
    }

    override fun onCleared() {
        remoteLiveData.removeObserver(remoteGetAllObserver)
        super.onCleared()
    }

}