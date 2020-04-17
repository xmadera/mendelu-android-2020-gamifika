package com.gamification.marketguards.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mojetodo.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.library.LibraryFragment
import com.gamification.marketguards.ui.map.MapFragment
import com.gamification.marketguards.ui.profile.ProfileActivity
import com.gamification.marketguards.ui.store.StoreActivity
import com.gamification.marketguards.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            val intent: Intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Market Guards"

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(listener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
    }


    private val listener = BottomNavigationView.OnNavigationItemSelectedListener {
        var selectedFragment: Fragment? = null

        when (it.itemId) {
            R.id.nav_story -> {
                selectedFragment = StoryFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_story)
            }
            R.id.nav_map -> {
                selectedFragment = MapFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_map)
            }
            R.id.nav_library -> {
                selectedFragment = LibraryFragment()
                SharedPreferencesManager.saveLastMainFragment(this,R.id.nav_library)
            }
            R.id.nav_store -> {
                startActivity(StoreActivity.createIntent(this))
            }
            else -> selectedFragment = DashboardFragment()
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

    fun setBottomNavItem() {
        bottom_navigation.selectedItemId = SharedPreferencesManager.getLastMainFragment(this)
    }
}
