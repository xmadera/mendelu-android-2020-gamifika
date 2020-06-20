package com.gamification.marketguards.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.model.missionsAndQuests.QuestSkillPreview
import com.gamification.marketguards.viewmodels.QuestDetailViewModel
import com.google.android.material.card.MaterialCardView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_quest_detail.*
import kotlinx.android.synthetic.main.content_quest_detail.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestDetailActivity : BaseActivity() {

    private lateinit var viewModel: QuestDetailViewModel

    companion object {
        fun createIntent(context: Context, id: Long): Intent {
            val intent: Intent = Intent(context, QuestDetailActivity::class.java)
            intent.putExtra(IntentConstants.QUEST_ID, id)
            return intent
        }
    }
    override val layout: Int = R.layout.activity_quest_detail

    private var quest_id: Long = -1
    private lateinit var quest: QuestDetail

    private var skillsPreviewQuest: List<QuestSkillPreview> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var skillsAdapter: QuestSkillsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.quest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this, QuestDetailViewModelFactory())
            .get(QuestDetailViewModel::class.java)

        quest_id = intent.getLongExtra(IntentConstants.QUEST_ID, -1)

        toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        getQuest()
    }

    inner class QuestSkillsAdapter : RecyclerView.Adapter<QuestSkillsAdapter.QuestSkillViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestSkillViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_skill_list, parent, false)
            return QuestSkillViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestSkillViewHolder, position: Int) {
            val skill = skillsPreviewQuest[position]
            holder.skillTitle.text = skill.experiences.toString() + " +" + skill.bonusExperiences.toString()
            when (skill.code) {
                "communication" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.communication))
                "products" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.products))
                "organization" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.organization))
                "prospecting" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.prospecting))
                "business" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.business))
                "networking" -> holder.skillWrapper.setBackgroundColor(resources.getColor(R.color.networking))
            }
        }

        override fun getItemCount() = skillsPreviewQuest.size

        inner class QuestSkillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val skillTitle: TextView = view.findViewById(R.id.skill_title)
            val skillWrapper: MaterialCardView = view.findViewById(R.id.skill_wrapper)
        }
    }

    private fun getQuest(){
        launch {
            quest = viewModel.findById(quest_id.toInt())
            skillsPreviewQuest = quest.questSkills
        }.invokeOnCompletion {
            runOnUiThread {
                fillLayout()
                skillsAdapter = QuestSkillsAdapter()
                layoutManager = LinearLayoutManager(this)
                quest_detail_skills_recyclerview.layoutManager = layoutManager
                quest_detail_skills_recyclerview.adapter = skillsAdapter
            }
        }
    }

    private fun fillLayout(){
        detail_quest_title.text = quest.title
        detail_quest_desc.text = quest.story

        if (quest.finished == null) {
            if (quest.activated != null) {
                detail_quest_button.visibility = View.VISIBLE
                detail_quest_button.text = "Finish Quest"
                detail_quest_button.setOnClickListener {
                    launch {
                        viewModel.finishQuest(quest.id)
                    }.invokeOnCompletion {
                        getQuest()
                    }
                }
            } else {
                detail_quest_button.visibility = View.VISIBLE
                detail_quest_button.text = "Start Quest"
                detail_quest_button.setOnClickListener {
                    launch {
                        viewModel.startQuest(quest.id)
                    }.invokeOnCompletion {
                        getQuest()
                    }
                }
            }
        } else {
            detail_quest_button.visibility = View.GONE
            detail_quest_notes.visibility = View.VISIBLE
            detail_quest_notes_sub.visibility = View.VISIBLE

            detail_quest_notes.setText(quest.note)
            detail_quest_notes.doAfterTextChanged {
                launch {
                    delay(1000)
                    val noteObject = JsonObject()
                    noteObject.addProperty("note",detail_quest_notes.text.toString())

                    viewModel.editQuestNotes(quest.id, noteObject)
                }
            }
        }
    }
}
