package com.gamification.marketguards.data.database.repository

import androidx.lifecycle.LiveData
import com.gamification.marketguards.data.model.skills.SkillPreview

interface ISkillRepository {
    fun getSkills(): LiveData<MutableList<SkillPreview>>
}