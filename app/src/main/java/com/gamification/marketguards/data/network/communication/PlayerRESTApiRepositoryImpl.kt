package com.gamification.marketguards.data.network.communication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.auth0.android.jwt.JWT
import com.gamification.marketguards.data.database.repository.IMissionRepository
import com.gamification.marketguards.data.database.repository.IPlayerRepository
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PlayerRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
): IPlayerRepository,  CoroutineScope by MainScope() {

    private val session = sessionManager

    private val playerApi: PlayerRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(PlayerRESTApi::class.java)

    override suspend fun getGameStatus(): GameStatus {
        return playerApi.getGameStatus()
    }

    fun getTokenInfo() : JWT {
        return JWT(session.fetchAuthToken()!!.removeRange(0,7))
    }
}