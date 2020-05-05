package com.gamification.marketguards.communication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gamification.marketguards.database.repository.IMissionRepository
import com.gamification.marketguards.model.Mission
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MissionRESTApiRepositoryImpl(private val context: Context) : IMissionRepository, CoroutineScope by MainScope() {

    private val missionsApi: MissionRESTApi = RetrofitConnection.getRetrofit()
        .create(MissionRESTApi::class.java);

    private val missionsLiveData = MutableLiveData<MutableList<Mission>>()

    override fun getAll(): LiveData<MutableList<Mission>> {
        launch {
            val response = missionsApi.getTasks()
            if (response.isSuccessful) {
                missionsLiveData.postValue(response.body())
            }
        }
        return missionsLiveData
    }

    override suspend fun findById(id: Long): Mission {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insert(mission: Mission): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(mission: Mission) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(mission: Mission) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}