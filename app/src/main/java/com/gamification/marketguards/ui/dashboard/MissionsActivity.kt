package com.gamification.marketguards.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.arch.BaseMVVMActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.Mission
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import kotlinx.android.synthetic.main.activity_missions.*
import kotlinx.android.synthetic.main.content_mission_list.*

class MissionsActivity: BaseMVVMActivity<DashBoardViewModel>(DashBoardViewModel::class.java) {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MissionsActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_missions

    private var missionsList: MutableList<Mission> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var missionsAdapter: MissionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.missions)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        missionsAdapter = MissionsAdapter()
        layoutManager = LinearLayoutManager(this)
        missionsRecyclerView.layoutManager = layoutManager
        missionsRecyclerView.adapter = missionsAdapter

        viewModel.getAll().observe(this, object : Observer<MutableList<Mission>> {
            override fun onChanged(t: MutableList<Mission>?) {
                t?.let {
                    val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback(){

                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            return missionsList[oldItemPosition].id == t[newItemPosition].id
                        }

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            return missionsList[oldItemPosition] == t[newItemPosition]
                        }

                        override fun getOldListSize() = missionsList.size

                        override fun getNewListSize() = t.size

                    })
                    diffUtil.dispatchUpdatesTo(missionsAdapter)
                    missionsList.clear()
                    missionsList.addAll(t)

                }
            }
        })

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
                resultIntent.putExtra(IntentConstants.MISSION_ID, missionsList[holder.adapterPosition].id)
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