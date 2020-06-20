package com.gamification.marketguards.ui.profile


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.model.player.GameStatus
import com.gamification.marketguards.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile_activity.*
import kotlinx.coroutines.launch

class ProfileActivity : BaseActivity() {

    private lateinit var viewModel: ProfileViewModel

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_profile

    private lateinit var gameStatus: GameStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel = ViewModelProvider(this, ProfileViewModelFactory())
            .get(ProfileViewModel::class.java)


        launch {
            gameStatus = viewModel.getGameStatus()
        } .invokeOnCompletion {
            runOnUiThread {
                profile_progressbar.max = gameStatus.experiencesRangeTo
                profile_progressbar.progress = gameStatus.experiences
                profile_progressbar_values.text = "${gameStatus.experiencesRangeFrom}/${gameStatus.experiencesRangeTo}"
                profile_level.text = "Level ${gameStatus.level}"
                profile_addressing_desc.text = "Addressing\n12"
                profile_analysis_desc.text = "Analysis\n12"
//                profile_consulting_desc.text = "Consulting\n12"
//                profile_services_desc.text = "Services\n12"
                profile_contacts_desc.text = "contacts\n12"
            }
        }

    }
}