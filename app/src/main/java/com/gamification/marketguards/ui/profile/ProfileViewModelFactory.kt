package com.gamification.marketguards.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.network.communication.restapi.PlayerRESTApiRepositoryImpl
import com.gamification.marketguards.data.network.communication.restapi.SkillRESTApiRepositoryImpl
import com.gamification.marketguards.viewmodels.ProfileViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ProfileViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                playerRepository = PlayerRESTApiRepositoryImpl(
                    App.appContext,
                    App.getSessionManager()
                ), skillRepository = SkillRESTApiRepositoryImpl(
                    App.appContext,
                    App.getSessionManager()
                )
            ) as T
        }
        throw IllegalArgumentException(App.appContext.getString(R.string.view_model_unknown_class))
    }
}
