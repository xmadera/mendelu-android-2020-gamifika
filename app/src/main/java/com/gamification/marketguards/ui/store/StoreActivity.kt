package com.gamification.marketguards.ui.store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamification.marketguards.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, StoreActivity::class.java)
        }
    }

    private lateinit var storePages: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)

        storePages = resources.getStringArray(R.array.StoreSubTabs)

        val storeAdapter = StoreAdapter(this, storePages.size)
        storeViewPager.adapter = storeAdapter

        TabLayoutMediator(tabLayout, storeViewPager) { tab, position ->
            tab.text = storePages[position].substringBefore(' ')
        }.attach()
    }


}