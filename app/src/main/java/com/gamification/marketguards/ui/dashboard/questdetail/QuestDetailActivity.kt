package com.gamification.marketguards.ui.dashboard.questdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.App
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.constants.IntentConstants
import com.gamification.marketguards.data.model.missionsAndQuests.QuestDetail
import com.gamification.marketguards.data.model.missionsAndQuests.QuestSkillPreview
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.viewmodels.QuestDetailViewModel
import com.google.android.material.card.MaterialCardView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_quest_detail.*
import kotlinx.android.synthetic.main.content_quest_detail.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

class QuestDetailActivity : BaseActivity() {

    private lateinit var viewModel: QuestDetailViewModel

    companion object {
        fun createIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, QuestDetailActivity::class.java)
            intent.putExtra(IntentConstants.QUEST_ID, id)
            return intent
        }
    }

    override val layout: Int = R.layout.activity_quest_detail

    private val SHOWCASE_ID = "Showcase300"
    private val SHOWCASE_ID2 = "Showcase400"

    private var quest_id: Long = -1
    private lateinit var quest: QuestDetail

    private var skillsPreviewQuest: List<QuestSkillPreview> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var skillsAdapter: QuestSkillsAdapter

    private var changes = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.quest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            QuestDetailViewModelFactory()
        )
            .get(QuestDetailViewModel::class.java)

        quest_id = intent.getLongExtra(IntentConstants.QUEST_ID, -1)

        MaterialShowcaseView.Builder(this)
            .setTarget(detail_quest_button)
            .setDismissText(getString(R.string.showcase_dismiss))
            .setContentText(getString(R.string.showcase_300_text))
            .setDelay(500)
            .singleUse(SHOWCASE_ID)
            .show()

        toolbar.setNavigationOnClickListener {
            if (changes) {
                val resultIntent = Intent().putExtra(
                    IntentConstants.MISSION_ID,
                    SharedPreferencesManager.getCurrentMissionID(this)
                )
                setResult(Activity.RESULT_OK, resultIntent)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }

        getQuest()
    }

    inner class QuestSkillsAdapter :
        RecyclerView.Adapter<QuestSkillsAdapter.QuestSkillViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestSkillViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_quest_skill_list, parent, false)
            return QuestSkillViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestSkillViewHolder, position: Int) {
            val skill = skillsPreviewQuest[position]
            val skillTitle = skill.experiences.toString() + "+ " + skill.bonusExperiences.toString()
            holder.skillTitle.text = skillTitle
            val context = App.appContext
            when (skill.code) {
                getString(R.string.skill_code_communication) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.communication)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_communication)
                }
                getString(R.string.skill_code_products) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.products)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_product)
                }
                getString(R.string.skill_code_organization) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.organization)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_organization)
                }
                getString(R.string.skill_code_prospecting) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.prospecting)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_prospecting)
                }
                getString(R.string.skill_code_business) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.business)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_business)
                }
                getString(R.string.skill_code_networking) -> {
                    holder.skillWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.networking)
                    )
                    holder.skillIcon.setImageResource(R.drawable.ic_networking)
                }
            }
        }

        override fun getItemCount() = skillsPreviewQuest.size

        inner class QuestSkillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val skillTitle: TextView = view.findViewById(R.id.skill_title)
            val skillWrapper: MaterialCardView = view.findViewById(R.id.skill_wrapper)
            val skillIcon: ImageView = view.findViewById(R.id.skill_icon)
        }
    }

    private fun getQuest() {
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

    private fun fillLayout() {
        detail_quest_title.text = quest.title
        detail_quest_desc.text = quest.story
        val xpTitle = "+ ${quest.experiences}"
        xp_title.text = xpTitle

        if (quest.finished == null) {
            if (quest.activated != null) {
                detail_quest_button.visibility = View.VISIBLE
                detail_quest_button.text = getString(R.string.quest_detail_button_finish_quest)
                detail_quest_button.setOnClickListener {
                    changes = true
                    launch {
                        viewModel.finishQuest(quest.id)
                    }.invokeOnCompletion {
                        getQuest()
                    }
                    MaterialShowcaseView.Builder(this)
                        .setTarget(detail_quest_notes)
                        .setDismissText(getString(R.string.showcase_dismiss))
                        .setContentText(getString(R.string.showcase_400_text))
                        .setDelay(500)
                        .singleUse(SHOWCASE_ID2)
                        .show()
                }
            } else {
                detail_quest_button.visibility = View.VISIBLE
                detail_quest_button.text = getString(R.string.quest_detail_button_start_quest)
                detail_quest_button.setOnClickListener {
                    changes = true
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
                    noteObject.addProperty("note", detail_quest_notes.text.toString())

                    viewModel.editQuestNotes(quest.id, noteObject)
                }
            }
        }
    }
}
