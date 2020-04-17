package com.gamification.marketguards.ui.profile


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class ProfileActivity: BaseActivity() {
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}