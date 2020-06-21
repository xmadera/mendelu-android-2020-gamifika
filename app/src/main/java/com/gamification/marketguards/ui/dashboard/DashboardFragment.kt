package com.gamification.marketguards.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.base.BaseFragment
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.missionsAndQuests.MissionDetail
import com.gamification.marketguards.data.model.missionsAndQuests.QuestPreview
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.ui.dashboard.missions.MissionsActivity
import com.gamification.marketguards.ui.dashboard.questdetail.QuestDetailActivity
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.launch
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView


class DashboardFragment : BaseFragment() {

    private lateinit var viewModel: DashBoardViewModel

    fun newInstance(missionId: Int): DashboardFragment {
        val newFragment = DashboardFragment()
        val dataBundle = Bundle()
        dataBundle.putInt(IntentConstants.MISSION_ID, missionId)
        newFragment.arguments = dataBundle
        return newFragment
    }

    override val layout: Int = R.layout.fragment_dashboard

    private lateinit var mission: MissionDetail
    private var selectedMissionId: Int = 0

    private lateinit var layoutManager: LinearLayoutManager
    private var questsList: MutableList<QuestPreview> = mutableListOf()
    private lateinit var questsAdapter: QuestsAdapter


    private val REQUEST_SELECT_MISSION = 100
    private val REQUEST_SELECT_QUEST = 200
    private val SHOWCASE_ID = "Showcase200"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(layout, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.dashboard)

        viewModel = ViewModelProvider(this, DashboardViewModelFactory())
            .get(DashBoardViewModel::class.java)

        val selectMissionButton = view!!.findViewById<Button>(R.id.button_select_mission)

        selectMissionButton.setOnClickListener {
            startActivityForResult(
                MissionsActivity.createIntent(activity!!),
                REQUEST_SELECT_MISSION
            )
        }

        MaterialShowcaseView.Builder(activity)
            .setTarget(selectMissionButton)
            .setDismissText(getString(R.string.showcase_dismiss))
            .setContentText(getString(R.string.showcase_100_text))
            .setDelay(500)
            .singleUse(SHOWCASE_ID)
            .show()

        selectedMissionId = arguments?.getInt(IntentConstants.MISSION_ID) ?: 0

        callMissionDetail()
        return view
    }

    private fun fillLayout() {
        SharedPreferencesManager.saveCurrentMissionID(App.appContext, selectedMissionId)
        if (selectedMissionId > 0) {
            mission_title?.text = mission.title
            mission_desc.visibility = View.VISIBLE
            mission_desc?.text = mission.story
        } else {
            mission_title.text = getString(R.string.all_quests)
            mission_desc.visibility = View.GONE
        }
        questsList =
            (mission.preparedQuests + mission.activeQuests + mission.finishedQuests).toMutableList()

        layoutManager = LinearLayoutManager(context!!)

        val questsRecyclerView =
            view?.findViewById<RecyclerView>(R.id.questsRecyclerView)
        questsAdapter = QuestsAdapter()
        questsRecyclerView?.layoutManager = layoutManager
        questsRecyclerView?.adapter = questsAdapter
    }

    private fun callMissionDetail() {
        launch {
            if (selectedMissionId > 0) {
                mission = viewModel.findById(selectedMissionId)
            } else {
                mission = viewModel.getAllQuests()
            }
        }.invokeOnCompletion {
            activity?.runOnUiThread {
                fillLayout()
            }
        }
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
                holder.questIcon.setImageResource(R.drawable.ic_ok)
            } else if (quest.activated != null) {
                holder.questIcon.setImageResource(R.drawable.ic_running)
            } else {
                holder.questIcon.setImageResource(R.drawable.ic_time)
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

        inner class QuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val questTitle: TextView = view.findViewById(R.id.quest_title)
            val questDesc: TextView = view.findViewById(R.id.quest_Desc)
            val questIcon: ImageView = view.findViewById(R.id.quest_icon)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_SELECT_QUEST || requestCode == REQUEST_SELECT_MISSION) && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedMissionId = data.getIntExtra(IntentConstants.MISSION_ID, 0)
            }
            callMissionDetail()
        }
    }
}