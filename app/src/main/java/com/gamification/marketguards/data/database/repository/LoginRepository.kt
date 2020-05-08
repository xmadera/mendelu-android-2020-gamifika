package com.gamification.marketguards.data.database.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.auth.LoginResponse
import com.gamification.marketguards.data.auth.LoginResult
import com.gamification.marketguards.data.network.LoginDataSourceInterface
import com.gamification.marketguards.data.network.SessionManagerInterface

interface LoginRepositoryInterface {
    val dataSource: LoginDataSourceInterface
    val sessionManager: SessionManagerInterface
    val successfullyLoggedIn: MutableLiveData<LoginResult>

    fun login(username: String, password: String)
}

class LoginRepository(
    val context: Context,
    override val dataSource: LoginDataSourceInterface,
    override val sessionManager: SessionManagerInterface
): LoginRepositoryInterface {

    private val _loggedIn = MutableLiveData<LoginResult>()
    override val successfullyLoggedIn = _loggedIn

    init {
        dataSource.loginResult.observeForever {

            if (it.success != null) {
                _loggedIn.value = LoginResult(true, null)
                persistLoginResponse(it.success)
            } else if (it.error != null) {
                _loggedIn.value = LoginResult(false, it.error.localizedMessage)
            }
        }
    }

    override fun login(username: String, password: String) {
        dataSource.login(username, password)
    }

    private fun persistLoginResponse(response: LoginResponse) {
        sessionManager.saveTokens(response.accessToken, response.refreshToken)
    }
}
