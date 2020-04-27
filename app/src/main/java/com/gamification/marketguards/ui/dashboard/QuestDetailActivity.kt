package com.gamification.marketguards.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.gamification.marketguards.constants.IntentConstants
import com.gamification.marketguards.R
import com.gamification.marketguards.model.Mission
import com.gamification.marketguards.model.Quest
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.main.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_quest_detail.*
import kotlinx.android.synthetic.main.content_mission_list.*
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.row_quest_list.*
import kotlinx.android.synthetic.main.row_quest_list.questTitle

class QuestDetailActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, QuestDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Quest"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        quest_title.text = "Quest Title"
        quest_desc.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
    }
}
