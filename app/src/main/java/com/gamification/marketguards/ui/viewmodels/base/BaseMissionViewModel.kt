package com.gamification.marketguards.ui.viewmodels.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gamification.marketguards.communication.MissionRESTApiRepositoryImpl
import com.gamification.marketguards.database.repository.IMissionRepository
import com.gamification.marketguards.database.repository.MissionLocalRepositoryImpl

abstract class BaseMissionViewModel (app: Application) : AndroidViewModel(app){
    protected var missionRepository: IMissionRepository = MissionLocalRepositoryImpl(app)
}