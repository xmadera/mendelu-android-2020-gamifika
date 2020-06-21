package com.gamification.marketguards.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.network.communication.restapi.MissionDetailRESTApiRepositoryImpl
import com.gamification.marketguards.viewmodels.DashBoardViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class DashboardViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashBoardViewModel::class.java)) {
            return DashBoardViewModel(
                missionPreviewRepository = MissionDetailRESTApiRepositoryImpl(
                    App.appContext,
                    App.getSessionManager()
                )
            ) as T
        }
        throw IllegalArgumentException(App.appContext.getString(R.string.view_model_unknown_class))
    }
}
