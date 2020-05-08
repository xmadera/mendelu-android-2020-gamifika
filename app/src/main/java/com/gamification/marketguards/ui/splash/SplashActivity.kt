package com.gamification.marketguards.ui.splash

import android.os.Bundle
import android.os.Handler
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager
import com.gamification.arch.BaseActivity
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.login.LoginActivity

class SplashActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        if (SharedPreferencesManager.isRunForFirstTime(this)) {
            Handler().postDelayed({
                continueToLogin()
            }, 3000)
        } else {
            if (SharedPreferencesManager.isLoggedIn(this)) {
                Handler().postDelayed({
                    continueToApp()
                }, 500)
            } else {
                Handler().postDelayed({
                    continueToLogin()
                }, 500)
            }
        }
    }

    private fun continueToApp() {
        SharedPreferencesManager.saveFirstRun(this)
        startActivity(MainActivity.createIntent(this))
        finish()
    }

    private fun continueToLogin() {
        startActivity(LoginActivity.createIntent(this))
        finish()
    }
}