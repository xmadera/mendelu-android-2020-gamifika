package com.gamification.marketguards.data.network.communication

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.model.auth.LoginResponse
import com.gamification.marketguards.data.model.auth.LoginResult
import com.gamification.marketguards.data.database.repository.ILoginRepository
import com.gamification.marketguards.data.network.communication.datasources.LoginDataSourceInterface
import com.gamification.marketguards.data.network.SessionManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class LoginRESTApiRepositoryImpl(
    val context: Context,
    override val dataSource: LoginDataSourceInterface,
    override val sessionManager: SessionManagerInterface
    ): ILoginRepository,  CoroutineScope by MainScope() {

        private val _loggedIn = MutableLiveData<LoginResult>()
        override val successfullyLoggedIn = _loggedIn

        init {
            dataSource.loginResult.observeForever {

                if (it.success != null) {
                    _loggedIn.value =
                        LoginResult(
                            true,
                            null
                        )
                    persistLoginResponse(it.success)
                } else if (it.error != null) {
                    _loggedIn.value =
                        LoginResult(
                            false,
                            it.error.localizedMessage
                        )
                }
            }
        }

        override fun login(login: String, password: String) {
            dataSource.login(login, password)
        }

        private fun persistLoginResponse(response: LoginResponse) {
            sessionManager.saveTokens(response.accessToken, response.refreshToken)
        }

}