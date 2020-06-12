package com.gamification.marketguards.data.network.communication

import android.content.Context
import com.gamification.marketguards.data.database.repository.IQuestRepository
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface
import com.gamification.marketguards.data.network.communication.service.ServiceGenerator
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.json.JSONObject
import java.util.HashMap

class QuestRESTApiRepositoryImpl(
    context: Context,
    sessionManager: SessionManagerInterface
): IQuestRepository,  CoroutineScope by MainScope() {

    private val questApi: QuestRESTApi = ServiceGenerator.getInstance(context, sessionManager)
        .create(QuestRESTApi::class.java)

    override suspend fun findById(id: Int): QuestDetail {
        return questApi.getQuestDetail(id)
    }

    suspend fun startQuest(id: Int) {
        questApi.startQuest(id)
    }

    suspend fun finishQuest(id: Int) {
        questApi.finishQuest(id)
    }

    suspend fun editQuestNotes(id: Int, note: JsonObject) {
        questApi.editQuestNotes(id, note)
    }

}