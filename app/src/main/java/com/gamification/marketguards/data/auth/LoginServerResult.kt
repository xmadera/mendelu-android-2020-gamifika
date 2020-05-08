package com.gamification.marketguards.data.auth

import com.gamification.marketguards.data.auth.LoginResponse
import java.io.IOException

data class LoginServerResult(val success: LoginResponse? = null, val error: IOException?)