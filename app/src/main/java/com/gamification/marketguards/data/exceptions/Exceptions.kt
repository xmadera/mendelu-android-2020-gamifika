package com.gamification.marketguards.data.exceptions

import java.io.IOException

class NoConnectionException : IOException() {
    override fun getLocalizedMessage(): String? {
        return "No internet"
    }
}

class GenericException : IOException() {
    override fun getLocalizedMessage(): String? {
        return "generic error"
    }
}

class InvalidCredentials : IOException() {
    override fun getLocalizedMessage(): String? {
        return "Invalid credentials"
    }
}