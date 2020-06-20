package com.gamification.marketguards.data.network.communication

import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.data.model.player.GameStatus
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query


interface PlayerRESTApi {

    @GET("player/game-status")
    suspend fun getGameStatus(): GameStatus

}