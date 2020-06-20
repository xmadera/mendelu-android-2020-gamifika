package com.gamification.marketguards.data.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.gamification.marketguards.R

class SharedPreferencesManager {

    companion object {
        private const val firstRun = "first_run"
        private const val authToken = "auth_token"
        private const val refreshToken = "refresh_token"

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        }

        @SuppressLint("ApplySharedPref")
        fun saveFirstRun(context: Context) {
            val editor = getSharedPreferences(context).edit()
            editor.putBoolean(firstRun, false)
            editor.commit()
        }

        fun isRunForFirstTime(context: Context): Boolean {
            val sharedPreferences = getSharedPreferences(context)
            return sharedPreferences
                .getBoolean(firstRun, true)
        }

        @SuppressLint("ApplySharedPref")
        fun saveAuthToken(context: Context, token: String) {
            val editor = getSharedPreferences(context).edit()
            editor.putString(authToken, token)
            editor.commit()
        }

        fun getAuthToken(context: Context): String? {
            val sharedPreferences = getSharedPreferences(context)
            return sharedPreferences
                .getString(authToken, null)
        }

        @SuppressLint("ApplySharedPref")
        fun saveRefreshToken(context: Context, token: String) {
            val editor = getSharedPreferences(context).edit()
            editor.putString(refreshToken, token)
            editor.commit()
        }

        fun getRefreshToken(context: Context): String? {
            val sharedPreferences = getSharedPreferences(context)
            return sharedPreferences
                .getString(refreshToken, null)
        }

        fun deleteTokens(context: Context) {
            val editor = getSharedPreferences(context).edit()
            editor.remove(authToken)
            editor.remove(refreshToken)
            editor.apply()
        }
    }
}