package com.gamification.marketguards.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.QuestPreview
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DashboardFragment: Fragment() {

    private lateinit var viewModel: DashBoardViewModel

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun newInstance(missionId: Int?): DashboardFragment {
        val newFragment = DashboardFragment()
        missionId?.let {
            val dataBundle = Bundle()
            dataBundle.putInt(IntentConstants.MISSION_ID, missionId)
            newFragment.arguments = dataBundle
        }
        return newFragment
    }

    private var questsList: MutableList<QuestPreview> = mutableListOf()
    private lateinit var mission: MissionDetail
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var questsAdapter: QuestsAdapter
    private var selectedMissionId: Int? = null

    private val REQUEST_SELECT_MISSION = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_dashboard, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.dashboard)


        viewModel = ViewModelProvider(this, DashboardViewModelFactory())
            .get(DashBoardViewModel::class.java)

        val selectMissionButton = view!!.findViewById<Button>(R.id.button_select_mission)

        selectMissionButton.setOnClickListener {
            startActivityForResult(MissionsActivity.createIntent(activity!!), REQUEST_SELECT_MISSION)
        }

        selectedMissionId = arguments?.getInt(IntentConstants.MISSION_ID)

        selectedMissionId?.let {
            uiScope.launch {
                mission = viewModel.findById(selectedMissionId!!)

                missionTitle.text = mission.title
                Log.d("missionTitle", selectedMissionId.toString())
                Log.d("missionTitle", mission.toString())
                missionDesc.text = mission.story

                questsList = mission.preparedQuests

                val recyclerView = view.findViewById<RecyclerView>(R.id.questsRecyclerView)
                questsAdapter = QuestsAdapter()
                layoutManager = LinearLayoutManager(activity)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = questsAdapter
            }
        }
//            ?: {
//
//        }

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
            holder.questDesc.text = quest.story
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_MISSION && resultCode == Activity.RESULT_OK){
            selectedMissionId = data!!.getIntExtra(IntentConstants.MISSION_ID, -1)

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment().newInstance(selectedMissionId)).commit()
        }
    }
}