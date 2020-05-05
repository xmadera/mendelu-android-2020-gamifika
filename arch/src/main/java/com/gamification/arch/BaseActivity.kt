package com.gamification.arch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@SuppressLint("Registered")
abstract class BaseActivity :
    AppCompatActivity(), CoroutineScope {

    abstract val layout: Int

    private val coroutinesJob = Job()
    override val coroutineContext: CoroutineContext
        get() = coroutinesJob + Dispatchers.IO
}