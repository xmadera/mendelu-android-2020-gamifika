package com.gamification.marketguards.data.network.communication.service

import android.content.Context
import com.gamification.marketguards.data.model.auth.TokenRefreshRequest
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenRefreshAuthenticator(
    val context: Context,
    private val sessionManager: SessionManagerInterface
) :
    Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = sessionManager.fetchRefreshToken() ?: ""
        val service = ServiceGenerator
            .getInstance(context, sessionManager)
            .createForRefreshToken(TokenRefreshService::class.java, refreshToken)

        val refreshTokenResponse =
            service.refreshToken(TokenRefreshRequest(refreshToken)).execute().body()

        sessionManager.saveTokens(
            refreshTokenResponse?.accessToken ?: "",
            refreshTokenResponse?.refreshToken ?: ""
        )

        return response.request.newBuilder()
            .header(
                "Authorization",
                refreshTokenResponse?.accessToken ?: ""
            )
            .addHeader("Content-Type", "application/json")
            .build()
    }
}
