package com.gamification.marketguards.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.network.communication.MissionRESTApiRepositoryImpl
import com.gamification.marketguards.data.network.communication.QuestRESTApiRepositoryImpl
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import com.gamification.marketguards.viewmodels.QuestDetailViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class QuestDetailViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestDetailViewModel::class.java)) {
            return QuestDetailViewModel(
                questRepository = QuestRESTApiRepositoryImpl(
                    App.appContext,
                    App.getSessionManager()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}