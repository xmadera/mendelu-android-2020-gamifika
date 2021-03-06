package com.gamification.marketguards.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.network.communication.datasources.LoginDataSource
import com.gamification.marketguards.data.network.communication.restapi.LoginRESTApiRepositoryImpl
import com.gamification.marketguards.data.network.communication.service.session.SessionManager
import com.gamification.marketguards.viewmodels.LoginViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRESTApiRepositoryImpl(
                    App.appContext,
                    sessionManager = SessionManager(
                        App.appContext
                    ),
                    dataSource = LoginDataSource(
                        App.appContext,
                        App.getSessionManager()
                    )
                )
            ) as T
        }
        throw IllegalArgumentException(App.appContext.getString(R.string.view_model_unknown_class))
    }
}
