package com.gamification.marketguards.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mojetodo.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.library.LibraryFragment
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.ui.map.MapFragment
import com.gamification.marketguards.ui.profile.ProfileActivity
import com.gamification.marketguards.ui.store.StoreFragment
import com.gamification.marketguards.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@SuppressLint("Registered")

open class BaseActivity : AppCompatActivity(), CoroutineScope {

    private val coroutinesJob = Job()
    override val coroutineContext: CoroutineContext
        get() = coroutinesJob + Dispatchers.IO
}