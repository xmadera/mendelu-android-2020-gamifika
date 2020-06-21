package com.gamification.marketguards.viewmodels

import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.network.communication.restapi.QuestRESTApiRepositoryImpl
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class QuestDetailViewModel(private val questRepository: QuestRESTApiRepositoryImpl) :
    ViewModel(), CoroutineScope by MainScope() {

    suspend fun findById(id: Int): QuestDetail {
        return questRepository.findById(id)
    }

    suspend fun startQuest(id: Int) {
        questRepository.startQuest(id)
    }

    suspend fun finishQuest(id: Int) {
        questRepository.finishQuest(id)
    }

    suspend fun editQuestNotes(id: Int, note: JsonObject) {
        questRepository.editQuestNotes(id, note)
    }

}