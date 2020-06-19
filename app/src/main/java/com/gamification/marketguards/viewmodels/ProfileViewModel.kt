package com.gamification.marketguards.viewmodels

import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.data.network.communication.PlayerRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class ProfileViewModel(private val playerRepository: PlayerRESTApiRepositoryImpl) : ViewModel(), CoroutineScope by MainScope() {

    suspend fun getGameStatus() : GameStatus {
        return playerRepository.getGameStatus()
    }

}