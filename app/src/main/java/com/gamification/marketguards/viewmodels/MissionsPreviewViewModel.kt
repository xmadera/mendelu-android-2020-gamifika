package com.gamification.marketguards.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.database.repository.MissionsLocalDetailRepositoryImpl
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.network.communication.restapi.MissionPreviewRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

open class MissionsPreviewViewModel(private val missionPreviewRepository: MissionPreviewRESTApiRepositoryImpl) :
    ViewModel(), CoroutineScope by MainScope() {

    private val localRepository = MissionsLocalDetailRepositoryImpl(App.appContext)
    private val remoteLiveData = missionPreviewRepository.getAll()

    private val remoteGetAllObserver =
        Observer<MutableList<MissionPreview>> { t ->
            t?.let {
                for (mission in it) {
                    if (mission.id != null) {
                        launch { localRepository.insert(mission) }
                    }
                }
            }
        }

    fun getAll(): LiveData<MutableList<MissionPreview>> {
        missionPreviewRepository.getAll().observeForever(remoteGetAllObserver)
        return localRepository.getAll()
    }

    override fun onCleared() {
        remoteLiveData.removeObserver(remoteGetAllObserver)
        super.onCleared()
    }
}