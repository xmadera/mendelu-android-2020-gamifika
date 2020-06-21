package com.gamification.marketguards.data.network.communication.service.session

import android.content.Context
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager


interface SessionManagerInterface {
    fun saveTokens(authToken: String, refreshToken: String)
    fun fetchAuthToken(): String?
    fun fetchRefreshToken(): String?
    fun deleteTokens()
}

class SessionManager(context: Context) :
    SessionManagerInterface {
    private var preferences = context

    override fun saveTokens(authToken: String, refreshToken: String) {
        SharedPreferencesManager.saveAuthToken(preferences, authToken)
        SharedPreferencesManager.saveRefreshToken(preferences, refreshToken)
    }

    override fun fetchAuthToken(): String? {
        return SharedPreferencesManager.getAuthToken(preferences)
    }

    override fun fetchRefreshToken(): String? {
        return SharedPreferencesManager.getRefreshToken(preferences)
    }

    override fun deleteTokens() {
        SharedPreferencesManager.deleteTokens(preferences)
    }
}