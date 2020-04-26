package com.gamification.marketguards.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginContinue.setOnClickListener {
            startActivity(MainActivity.createIntent(this))
            finish()
        }
    }
}