package com.gamification.marketguards.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gamification.arch.BaseActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.dashboard.DashboardFragment
import com.gamification.marketguards.ui.library.LibraryFragment
import com.gamification.marketguards.ui.map.MapFragment
import com.gamification.marketguards.ui.profile.ProfileActivity
import com.gamification.marketguards.ui.store.StoreFragment
import com.gamification.marketguards.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(listener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment().newInstance(null)).commit()
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener {
        var selectedFragment: Fragment

        when (it.itemId) {
            R.id.nav_story -> {
                selectedFragment = StoryFragment()
            }
            R.id.nav_map -> {
                selectedFragment = MapFragment()
            }
            R.id.nav_library -> {
                selectedFragment = LibraryFragment()
            }
            R.id.nav_store -> {
                selectedFragment = StoreFragment()
            }
            else -> {
                selectedFragment = DashboardFragment().newInstance(null)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment).commit()
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
}
