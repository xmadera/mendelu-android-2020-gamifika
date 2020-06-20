package com.gamification.marketguards.data.network.communication.RESTApi

import android.content.Context
import com.auth0.android.jwt.JWT
import com.gamification.marketguards.data.database.repository.IPlayerRepository
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class PlayerRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
) : IPlayerRepository, CoroutineScope by MainScope() {

    private val session = sessionManager

    private val playerApi: PlayerRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(PlayerRESTApi::class.java)

    override suspend fun getGameStatus(): GameStatus {
        return playerApi.getGameStatus()
    }

    fun getTokenInfo(): JWT {
        return JWT(session.fetchAuthToken()!!.removeRange(0, 7))
    }
}