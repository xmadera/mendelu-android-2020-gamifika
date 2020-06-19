package com.gamification.marketguards.data.network.communication.service

import android.content.Context
import com.gamification.marketguards.R

interface SessionManagerInterface {
    fun saveTokens(authToken: String, refreshToken: String)
    fun fetchAuthToken(): String?
    fun fetchRefreshToken(): String?
    fun deleteTokens()
}

class SessionManager(context: Context) :
    SessionManagerInterface {
    private var preferences = context
        .getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

    val AUTH_TOKEN = "auth_token"
    val REFRESH_TOKEN = "refresh_token"

    override fun saveTokens(authToken: String, refreshToken: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN, authToken)
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    override fun fetchAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    override fun fetchRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN, null)
    }

    override fun deleteTokens() {
        val editor = preferences.edit()
        editor.remove(AUTH_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.apply()
    }
}