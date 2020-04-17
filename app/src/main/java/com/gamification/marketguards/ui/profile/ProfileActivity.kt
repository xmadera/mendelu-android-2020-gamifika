package com.gamification.marketguards.ui.profile


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.store.StoreActivity
import com.gamification.marketguards.ui.store.StoreAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.content_store.*

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