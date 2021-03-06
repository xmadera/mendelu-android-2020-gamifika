package com.gamification.marketguards.data.network.communication.restapi

import com.gamification.marketguards.data.model.player.GameStatus
import retrofit2.http.GET


interface PlayerRESTApi {

    @GET("player/game-status")
    suspend fun getGameStatus(): GameStatus

}