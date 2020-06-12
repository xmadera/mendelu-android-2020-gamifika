package com.gamification.marketguards.data.network.communication.service

import com.gamification.marketguards.data.model.auth.LoginRequest
import com.gamification.marketguards.data.model.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("security/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}