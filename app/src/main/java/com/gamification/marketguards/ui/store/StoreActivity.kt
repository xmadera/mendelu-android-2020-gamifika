package com.gamification.marketguards.ui.store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojetodo.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.activity_store.toolbar
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.coroutines.launch

class StoreActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, StoreActivity::class.java)
        }
    }

    private lateinit var storePages: Array<String>
    private lateinit var adapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Store"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        storePages = resources.getStringArray(R.array.StoreSubTabs)

        setupViewPager()

        TabLayoutMediator(tabLayout, storeViewPager) { tab, position ->
            tab.text = storePages[position].substringBefore(' ')
        }.attach()
    }

    private fun setupViewPager() {
        adapter = StoreAdapter(this)
        for (position in storePages.indices){
            val fragment: StoreFragment = StoreFragment().newInstance(storePages[position])
            adapter.addFragment(fragment)
        }
        storeViewPager!!.adapter = adapter
    }

}