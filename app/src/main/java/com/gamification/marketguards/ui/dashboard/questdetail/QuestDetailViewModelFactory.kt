package com.gamification.marketguards.ui.dashboard.questdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.network.communication.RESTApi.QuestRESTApiRepositoryImpl
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
        throw IllegalArgumentException(App.appContext.getString(R.string.view_model_unknown_class))
    }
}
