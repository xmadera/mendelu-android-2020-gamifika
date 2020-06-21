package com.gamification.marketguards.data.base

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment :
    Fragment(), CoroutineScope {

    abstract val layout: Int

    private val coroutinesJob = Job()
    override val coroutineContext: CoroutineContext
        get() = coroutinesJob + Dispatchers.IO
}