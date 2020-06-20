package com.gamification.marketguards.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.auth0.android.jwt.JWT
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.data.model.skills.SkillPreview
import com.gamification.marketguards.data.network.communication.RESTApi.PlayerRESTApiRepositoryImpl
import com.gamification.marketguards.data.network.communication.RESTApi.SkillRESTApiRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class ProfileViewModel(
    private val playerRepository: PlayerRESTApiRepositoryImpl,
    private val skillRepository: SkillRESTApiRepositoryImpl
) : ViewModel(), CoroutineScope by MainScope() {

    suspend fun getGameStatus(): GameStatus {
        return playerRepository.getGameStatus()
    }

    fun getTokenInfo(): JWT? {
        return playerRepository.getTokenInfo()
    }

    fun getSkills(): LiveData<MutableList<SkillPreview>> {
        return skillRepository.getSkills()
    }

}