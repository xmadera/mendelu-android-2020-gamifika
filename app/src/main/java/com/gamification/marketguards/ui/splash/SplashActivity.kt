package com.gamification.marketguards.ui.splash

import android.os.Bundle
import android.os.Handler
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.ui.login.LoginActivity
import com.gamification.marketguards.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SharedPreferencesManager.isRunForFirstTime(this)) {
            Handler().postDelayed({
                continueToApp()
            }, 3000)
        } else {
            Handler().postDelayed({
                continueToApp()
            }, 500)
        }
    }

    private fun continueToApp() {
        SharedPreferencesManager.saveFirstRun(this)
        if (App.isLoggedIn()) {
            startActivity(MainActivity.createIntent(this))
        } else {
            startActivity(LoginActivity.createIntent(this))
        }
        finish()
    }
}