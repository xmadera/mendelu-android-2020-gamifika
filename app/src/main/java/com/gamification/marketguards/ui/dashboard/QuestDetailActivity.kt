package com.gamification.marketguards.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gamification.marketguards.R
import kotlinx.android.synthetic.main.activity_quest_detail.*

class QuestDetailActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, QuestDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.quest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.detail_quest_title).text = "Quest Title"
        findViewById<TextView>(R.id.detail_quest_desc).text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
    }
}
