package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.model.auth.LoginResult
import com.gamification.marketguards.data.network.communication.datasources.LoginDataSourceInterface
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

interface LoginRepositoryInterface {
    val dataSource: LoginDataSourceInterface
    val sessionManager: SessionManagerInterface
    val successfullyLoggedIn: MutableLiveData<LoginResult>

    fun login(username: String, password: String)
}
