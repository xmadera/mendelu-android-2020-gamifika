package com.gamification.marketguards.ui.profile


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.data.model.skills.SkillPreview
import com.gamification.marketguards.ui.settings.SettingsActivity
import com.gamification.marketguards.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile_activity.*
import kotlinx.android.synthetic.main.content_skills_list.*
import kotlinx.coroutines.launch

class ProfileActivity : BaseActivity() {

    private lateinit var viewModel: ProfileViewModel

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_profile
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var skillsAdapter: SkillsAdapter

    private lateinit var jwt: JWT
    private lateinit var gameStatus: GameStatus
    private var skillsPreview: MutableList<SkillPreview> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel = ViewModelProvider(this, ProfileViewModelFactory())
            .get(ProfileViewModel::class.java)

        skillsAdapter = SkillsAdapter()
        layoutManager = LinearLayoutManager(this)
        skills_recyclerview.layoutManager = layoutManager
        skills_recyclerview.adapter = skillsAdapter

        launch {
            gameStatus = viewModel.getGameStatus()
        }.invokeOnCompletion {
            runOnUiThread {
                profile_progressbar.max = gameStatus.experiencesRangeTo
                profile_progressbar.progress = gameStatus.experiences
                profile_money.text = gameStatus.currency.toString()
                profile_xp.text = "${gameStatus.experiences}/${gameStatus.experiencesRangeTo}"
                profile_login.text = "Jan"
                profile_level.text =
                    getString(R.string.profile_sources_level) + " ${gameStatus.level}"
                profile_addressing_desc.text =
                    getString(R.string.profile_sources_addressing) + "\n${gameStatus.addressing}"
                profile_analysis_desc.text =
                    getString(R.string.profile_sources_analysis) + "\n${gameStatus.analysis}"
                profile_consulting_desc.text =
                    getString(R.string.profile_sources_consulting) + "\n${gameStatus.consulting}"
                profile_services_desc.text =
                    getString(R.string.profile_sources_services) + "\n${gameStatus.services}"
                profile_contacts_desc.text =
                    getString(R.string.profile_sources_contacts) + "\n${gameStatus.contacts}"
            }
        }

        launch {
            jwt = viewModel.getTokenInfo()!!
        }.invokeOnCompletion {
            //  TODO: Info from JWT token
        }
        viewModel.getSkills().observe(this, object : Observer<MutableList<SkillPreview>> {
            override fun onChanged(t: MutableList<SkillPreview>?) {
                t?.let {
                    val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                        override fun areItemsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            return skillsPreview[oldItemPosition].id == t[newItemPosition].id
                        }

                        override fun areContentsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            return skillsPreview[oldItemPosition] == t[newItemPosition]
                        }

                        override fun getOldListSize() = skillsPreview.size

                        override fun getNewListSize() = t.size

                    })
                    diffUtil.dispatchUpdatesTo(skillsAdapter)
                    skillsPreview.clear()
                    skillsPreview.addAll(t)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile_settings -> {
                startActivity(SettingsActivity.createIntent(this))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class SkillsAdapter : RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_profile_skill_list, parent, false)
            return SkillsViewHolder(view)
        }


        override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
            val skill = skillsPreview[position]
            holder.skillTitle.text = skill.title
            holder.skillLevel.text =
                getString(R.string.profile_sources_level) + "${skill.level} | ${skill.experiences}/${skill.experiencesToNextLevel}"
            holder.skillProgressbar.progress = skill.experiences
            holder.skillProgressbar.max = skill.experiencesToNextLevel

            if (skill.experiences > 0) {
                when (skill.code) {
                    getString(R.string.skill_code_communication) -> holder.skillIcon.setImageResource(
                        R.drawable.ic_communication
                    )
                    getString(R.string.skill_code_products) -> holder.skillIcon.setImageResource(R.drawable.ic_product)
                    getString(R.string.skill_code_organization) -> holder.skillIcon.setImageResource(
                        R.drawable.ic_organization
                    )
                    getString(R.string.skill_code_prospecting) -> holder.skillIcon.setImageResource(
                        R.drawable.ic_prospecting
                    )
                    getString(R.string.skill_code_business) -> holder.skillIcon.setImageResource(R.drawable.ic_business)
                    getString(R.string.skill_code_networking) -> holder.skillIcon.setImageResource(R.drawable.ic_networking)
                }
            } else {
                holder.skillIcon.setImageResource(R.drawable.ic_skill_disabled)
                holder.skillTitle.setTextColor(resources.getColor(R.color.disabled))
                holder.skillLevel.setTextColor(resources.getColor(R.color.disabled))
            }
        }

        override fun getItemCount() = skillsPreview.size

        inner class SkillsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val skillTitle: TextView = view.findViewById(R.id.skill_title)
            val skillLevel: TextView = view.findViewById(R.id.skill_level)
            val skillIcon: ImageView = view.findViewById(R.id.skill_icon)
            val skillProgressbar: ProgressBar = view.findViewById(R.id.skill_progressbar)
        }
    }
}