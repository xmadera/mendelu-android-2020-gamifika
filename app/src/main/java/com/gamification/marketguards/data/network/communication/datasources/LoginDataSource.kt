package com.gamification.marketguards.data.network.communication.datasources

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.data.exceptions.GenericException
import com.gamification.marketguards.data.exceptions.InvalidCredentials
import com.gamification.marketguards.data.exceptions.NoConnectionException
import com.gamification.marketguards.data.model.auth.LoginRequest
import com.gamification.marketguards.data.model.auth.LoginResponse
import com.gamification.marketguards.data.model.auth.LoginServerResult
import com.gamification.marketguards.data.network.communication.service.AuthService
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import com.gamification.marketguards.data.network.utility.sha256Hash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


interface LoginDataSourceInterface {
    val loginResult: LiveData<LoginServerResult>
    fun login(username: String, password: String)
}

class LoginDataSource(context: Context, sessionManager: SessionManagerInterface) :
    LoginDataSourceInterface {

    private val _loginResponse = MutableLiveData<LoginServerResult>()
    private val authService = ServiceGenerator.getInstance(
        context,
        sessionManager
    ).create(AuthService::class.java)

    override val loginResult: LiveData<LoginServerResult> = _loginResponse

    override fun login(username: String, password: String) {
        try {
            authService.login(LoginRequest(username, password.sha256Hash()))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _loginResponse.value =
                            LoginServerResult(null, IOException(t.localizedMessage))
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {

                        if (response.isSuccessful && response.body()?.accessToken != null && response.body()?.refreshToken != null) {
                            _loginResponse.value = LoginServerResult(
                                LoginResponse(
                                    response.body()?.accessToken ?: "",
                                    response.body()?.refreshToken ?: ""
                                ),
                                null
                            )
                        } else if (response.code() == 400) {
                            _loginResponse.value = LoginServerResult(null, InvalidCredentials())
                        } else {
                            _loginResponse.value = LoginServerResult(null, GenericException())
                        }
                    }
                })

        } catch (e: NoConnectionException) {
            Log.e("Connection", "no internet", e)
        }
    }
}