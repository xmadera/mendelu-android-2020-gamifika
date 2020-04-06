package com.example.mojetodo.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    companion object {
        private val fileName = "gamsp"
        private val firstRun = "first_run"

        /**
         * Returns the object to access the shared preferences.
         * @param context context
         * @return SharedPreferences object
         */
        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        }

        /**
         * Saves a boolean value to the shared preferences.
         * The value represents if the app is run for the first time.
         * @param context context
         */
        @SuppressLint("ApplySharedPref")
        fun saveFirstRun(context: Context) {
            val editor = getSharedPreferences(context).edit()
            editor.putBoolean(firstRun, false)
            editor.commit()
        }

        /**
         * Returns true of the app is run for the first time.
         * @param context context
         * @return Returns true of the app is run for the first time.
         */
        fun isRunForFirstTime(context: Context): Boolean {
            val sharedPreferences = getSharedPreferences(context)
            return sharedPreferences
                .getBoolean(firstRun, true)
        }
    }
}