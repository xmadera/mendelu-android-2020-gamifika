package com.gamification.marketguards.ui.dashboard.missions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.missionsAndQuests.MissionPreview
import com.gamification.marketguards.ui.dashboard.DashboardViewModelFactory
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import kotlinx.android.synthetic.main.activity_missions.*
import kotlinx.android.synthetic.main.content_mission_list.*

class MissionsActivity : BaseActivity() {

    private lateinit var viewModel: DashBoardViewModel

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MissionsActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_missions

    private var missionsPreview: MutableList<MissionPreview> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var missionsAdapter: MissionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.missions)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }


        viewModel = ViewModelProvider(
            this,
            DashboardViewModelFactory()
        )
            .get(DashBoardViewModel::class.java)

        missionsAdapter = MissionsAdapter()
        layoutManager = LinearLayoutManager(this)
        missionsRecyclerView.layoutManager = layoutManager
        missionsRecyclerView.adapter = missionsAdapter

        mission_list_all_quests.setOnClickListener {
            val resultIntent = Intent().putExtra(
                IntentConstants.MISSION_ID,
                0
            )
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        viewModel.getAll().observe(this, object : Observer<MutableList<MissionPreview>> {
            override fun onChanged(t: MutableList<MissionPreview>?) {
                t?.let {
                    val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                        override fun areItemsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            return missionsPreview[oldItemPosition].id == t[newItemPosition].id
                        }

                        override fun areContentsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            return missionsPreview[oldItemPosition] == t[newItemPosition]
                        }

                        override fun getOldListSize() = missionsPreview.size

                        override fun getNewListSize() = t.size

                    })
                    diffUtil.dispatchUpdatesTo(missionsAdapter)
                    missionsPreview.clear()
                    missionsPreview.addAll(t)
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
            val mission = missionsPreview[position]
            holder.missionTitle.text = mission.title
            if (anyQuests(holder.adapterPosition)) {
                if (mission.finishedQuests < mission.totalQuests) {
                    holder.finishedQuests.text = mission.finishedQuests.toString() + "/"
                    holder.totalQuests.text = mission.totalQuests.toString()
                } else {
                    holder.finishedIcon.setImageResource(R.drawable.ic_ok)
                    holder.finishedIcon.visibility = View.VISIBLE
                }
                holder.missionTitle.setTextColor(resources.getColor(R.color.colorPrimary))
                holder.itemView.setOnClickListener {
                    val resultIntent = Intent().putExtra(
                        IntentConstants.MISSION_ID,
                        missionsPreview[holder.adapterPosition].id
                    )
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            } else {
                holder.finishedIcon.setImageResource(R.drawable.ic_locked)
                holder.finishedIcon.setColorFilter(resources.getColor(R.color.disabled))
                holder.finishedIcon.visibility = View.VISIBLE
            }
        }

        private fun anyQuests(position: Int): Boolean {
            return missionsPreview[position].totalQuests > 0
        }

        override fun getItemCount() = missionsPreview.size

        inner class MissionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val missionTitle: TextView = view.findViewById(R.id.mission_title)
            val totalQuests: TextView = view.findViewById(R.id.total_quests)
            val finishedQuests: TextView = view.findViewById(R.id.finished_quests)
            val finishedIcon: ImageView = view.findViewById(R.id.finished_icon)
        }
    }
}