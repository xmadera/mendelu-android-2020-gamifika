package com.gamification.marketguards.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mojetodo.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.library.LibraryFragment
import com.gamification.marketguards.ui.map.MapFragment
import com.gamification.marketguards.ui.profile.ProfileActivity
import com.gamification.marketguards.ui.store.StoreAdapter
import com.gamification.marketguards.ui.store.StoreFragment
import com.gamification.marketguards.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.android.synthetic.main.fragment_store.*

class MainActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var storePages: Array<String>
    private lateinit var adapter: StoreAdapter
    private var firstInit: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Market Guards"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(listener)

        storePages = resources.getStringArray(R.array.StoreSubTabs)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener {
        var selectedFragment: Fragment? = null

        when (it.itemId) {
            R.id.nav_story -> {
                selectedFragment = StoryFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_story)
                hideStore()
            }
            R.id.nav_map -> {
                selectedFragment = MapFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_map)
                hideStore()
            }
            R.id.nav_library -> {
                selectedFragment = LibraryFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_library)
                hideStore()
            }
            R.id.nav_store -> {
                if (firstInit) {
                    setupViewPager()
                    setupTabLayout()
                }
                showStore()
                firstInit = false
            }
            else -> {
                selectedFragment = DashboardFragment()
                tabLayout.visibility = View.GONE
                store_fragment.visibility = View.GONE
            }
        }
            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit()
            }
        true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(ProfileActivity.createIntent(this))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showStore() {
        if (tabLayout != null && store_fragment != null) {
            tabLayout.visibility = View.VISIBLE
            store_fragment.visibility = View.VISIBLE
        }
        fragment_container.visibility = View.GONE
    }

    private fun hideStore() {
        if (tabLayout != null && store_fragment != null) {
            tabLayout.visibility = View.GONE
            store_fragment.visibility = View.GONE
        }
        fragment_container.visibility = View.VISIBLE
    }

    private fun setupViewPager() {
        adapter = StoreAdapter(this)
        for (position in storePages.indices){
            val fragment: StoreFragment = StoreFragment().newInstance(storePages[position])
            adapter.addFragment(fragment)
        }
        storeViewPager!!.adapter = adapter
        storeViewPager.isUserInputEnabled = false
    }

    private fun setupTabLayout() {
        TabLayoutMediator(tabLayout, storeViewPager) { tab, position ->
                tab.text = storePages[position].substringBefore(' ')
            }.attach()
    }
}
