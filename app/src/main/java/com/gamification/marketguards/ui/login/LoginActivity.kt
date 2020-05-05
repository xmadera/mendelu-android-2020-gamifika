package com.gamification.marketguards.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gamification.arch.BaseActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        login_button.setOnClickListener {
            startActivity(MainActivity.createIntent(this))
            finish()
        }
    }
}