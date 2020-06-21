package com.gamification.marketguards.data.exceptions

import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import java.io.IOException

class NoConnectionException : IOException() {
    override fun getLocalizedMessage(): String? {
        return App.appContext.getString(R.string.exception_no_internet)
    }
}

class GenericException : IOException() {
    override fun getLocalizedMessage(): String? {
        return App.appContext.getString(R.string.exception_generic_error)
    }
}

class InvalidCredentials : IOException() {
    override fun getLocalizedMessage(): String? {
        return App.appContext.getString(R.string.exception_invalid_credentials)
    }
}