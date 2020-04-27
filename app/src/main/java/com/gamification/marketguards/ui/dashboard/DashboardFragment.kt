package com.gamification.marketguards.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.model.Quest
import com.gamification.marketguards.ui.main.MainActivity


class DashboardFragment: Fragment() {

    private var questsList: MutableList<Quest> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var questsAdapter: QuestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_dashboard, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.dashboard)

        val selectMissionButton = view!!.findViewById<Button>(R.id.button_select_mission)

        selectMissionButton.setOnClickListener {
            startActivity(MissionsActivity.createIntent(activity!!))
        }

        //
        val quest1 = Quest("Quest 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's...")
        val quest2 = Quest("Quest 2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's...")
        questsList.add(quest1)
        questsList.add(quest2)

        view.findViewById<TextView>(R.id.missionTitle).text = "Mission Title"
        view.findViewById<TextView>(R.id.missionDesc).text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
        //

        val recyclerView = view.findViewById<RecyclerView>(R.id.questsRecyclerView)
        questsAdapter = QuestsAdapter()
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = questsAdapter

        return view
    }

    inner class QuestsAdapter : RecyclerView.Adapter<QuestsAdapter.MissionViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_quest_list, parent, false)
            return MissionViewHolder(view)
        }


        override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
            val quest = questsList[position]
            holder.questTitle.text = quest.title
            holder.questDesc.text = quest.desc
            holder.itemView.setOnClickListener {
                startActivity(QuestDetailActivity.createIntent(activity!!))
            }
        }

        override fun getItemCount() = questsList.size

        inner class MissionViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val questTitle: TextView = view.findViewById(R.id.questTitle)
            val questDesc: TextView = view.findViewById(R.id.questDesc)
        }
    }
}