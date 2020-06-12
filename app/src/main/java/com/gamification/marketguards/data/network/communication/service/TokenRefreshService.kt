package com.gamification.marketguards.data.network.communication.service

import com.gamification.marketguards.data.model.auth.LoginResponse
import com.gamification.marketguards.data.model.auth.TokenRefreshRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenRefreshService {
    @POST("security/login/extend")
    fun refreshToken(@Body refreshRequest: TokenRefreshRequest): Call<LoginResponse>
}