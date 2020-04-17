package com.gamification.marketguards.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mojetodo.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!SharedPreferencesManager.isRunForFirstTime(this)) {
            Handler().postDelayed({
                startActivity(MainActivity.createIntent(this))
                finish()
            }, 500)
        } else {
            Handler().postDelayed({
                continueToApp()
            }, 3000)
        }
    }

    private fun continueToApp() {
        SharedPreferencesManager.saveFirstRun(this)
        startActivity(MainActivity.createIntent(this))
        finish()
    }
}