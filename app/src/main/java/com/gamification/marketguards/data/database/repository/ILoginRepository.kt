package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.model.auth.LoginResult
import com.gamification.marketguards.data.network.communication.datasources.LoginDataSourceInterface
import com.gamification.marketguards.data.network.SessionManagerInterface

interface ILoginRepository {
    val dataSource: LoginDataSourceInterface
    val sessionManager: SessionManagerInterface
    val successfullyLoggedIn: MutableLiveData<LoginResult>

    fun login(login: String, password: String)
}
