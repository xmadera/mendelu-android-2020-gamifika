package com.gamification.marketguards.ui.dashboard


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.constants.IntentConstants
import com.gamification.marketguards.model.Mission
import com.gamification.marketguards.ui.BaseActivity
import com.gamification.marketguards.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_mission_list.*

class MissionsActivity: BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MissionsActivity::class.java)
        }
    }

    private var missionsList: MutableList<Mission> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var missionsAdapter: MissionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missions)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Missions"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val mission1 = Mission("Mission 1", "Description 1")
        val mission2 = Mission("Mission 2", "Description 2")
        missionsList.add(mission1)
        missionsList.add(mission2)
        //

        missionsAdapter = MissionsAdapter()
        layoutManager = LinearLayoutManager(this)
        missionsRecyclerView.layoutManager = layoutManager
        missionsRecyclerView.adapter = missionsAdapter

    }

    inner class MissionsAdapter : RecyclerView.Adapter<MissionsAdapter.MissionViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_mission_list, parent, false)
            return MissionViewHolder(view)
        }


        override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
            val mission = missionsList[position]
            holder.missionTitle.text = mission.title
            holder.itemView.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra(IntentConstants.MISSION_ID, 1)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

        override fun getItemCount() = missionsList.size

        inner class MissionViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val missionTitle: TextView = view.findViewById(R.id.missionTitle)
        }
    }
}