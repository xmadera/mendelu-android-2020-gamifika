package com.gamification.marketguards.ui.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gamification.marketguards.BuildConfig
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseActivity
import com.gamification.marketguards.data.sharedpreferences.SharedPreferencesManager
import com.gamification.marketguards.ui.login.LoginActivity
import com.gamification.marketguards.viewmodels.DashBoardViewModel
import kotlinx.android.synthetic.main.activity_missions.toolbar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    private lateinit var viewModel: DashBoardViewModel

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        settings_logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.profile_logout_message))

            builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
                SharedPreferencesManager.deleteTokens(this)
                startActivity(LoginActivity.createIntent(this))
            }

            builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        settings_app_version.text = "App version" + ": " + "${BuildConfig.VERSION_NAME}"

    }
}