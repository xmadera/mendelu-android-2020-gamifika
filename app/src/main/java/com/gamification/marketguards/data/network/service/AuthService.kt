package com.gamification.marketguards.data.network.service

import com.gamification.marketguards.data.auth.LoginRequest
import com.gamification.marketguards.data.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("security/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}