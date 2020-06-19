package com.gamification.marketguards.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
import kotlinx.android.synthetic.main.content_quest_detail.*
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

    private lateinit var mission: MissionDetail
    private var selectedMissionId: Int? = null

    private lateinit var layoutManager: LinearLayoutManager
    private var questsList: MutableList<QuestPreview> = mutableListOf()
    private lateinit var questsAdapter: QuestsAdapter


    private val REQUEST_SELECT_MISSION = 100
    private val REQUEST_SELECT_QUEST = 200

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

        fillLayout()
        return view
    }

    private fun fillLayout() {
        selectedMissionId?.let {
            uiScope.launch {
                if (selectedMissionId != 0) {
                    mission = viewModel.findById(selectedMissionId!!)
                } else {
                    mission = viewModel.getAllQuests()
                }
            } .invokeOnCompletion {
                if (selectedMissionId != 0) {
                    mission_title?.text = mission.title
                    mission_desc?.text = mission.story
                } else {
                    mission_title.text = "All quests"
                    mission_desc.visibility = View.GONE
                }

                questsList = (mission.preparedQuests + mission.activeQuests + mission.finishedQuests).toMutableList()

                layoutManager = LinearLayoutManager(context!!)

                val questsRecyclerView = view?.findViewById<RecyclerView>(R.id.questsRecyclerView)
                questsAdapter = QuestsAdapter()
                questsRecyclerView?.layoutManager = layoutManager
                questsRecyclerView?.adapter = questsAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun anyActiveQuests(quests: List<QuestPreview>): Boolean {
        return quests.isNotEmpty()
    }

    inner class QuestsAdapter : RecyclerView.Adapter<QuestsAdapter.QuestViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_quest_list, parent, false)
            return QuestViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
            val quest = questsList[position]
            holder.questTitle.text = quest.title
            holder.questDesc.text = quest.story
            if (quest.finished != null) {
                    holder.questIcon.setImageResource(R.drawable.ic_baseline_done)
            } else if (quest.activated != null) {
                holder.questIcon.setImageResource(R.drawable.ic_baseline_fast_forward)
            } else {
                holder.questIcon.setImageResource(R.drawable.ic_baseline_play_arrow)
            }
            holder.itemView.setOnClickListener {
                startActivityForResult(
                    QuestDetailActivity.createIntent(
                        context!!,
                        questsList[holder.adapterPosition].id.toLong()
                    ), REQUEST_SELECT_QUEST
                )
            }
        }

        override fun getItemCount() = questsList.size

        inner class QuestViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val questTitle: TextView = view.findViewById(R.id.quest_title)
            val questDesc: TextView = view.findViewById(R.id.quest_Desc)
            val questIcon: ImageView = view.findViewById(R.id.quest_icon)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_MISSION && resultCode == Activity.RESULT_OK){
            selectedMissionId = data!!.getIntExtra(IntentConstants.MISSION_ID, -1)

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment().newInstance(selectedMissionId)).commit()
        }
        if (requestCode == REQUEST_SELECT_QUEST && resultCode == Activity.RESULT_OK){
            fillLayout()
        }
    }
}