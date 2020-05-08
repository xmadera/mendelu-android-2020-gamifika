package com.gamification.marketguards.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gamification.arch.BaseActivity
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.main.MainActivity
import com.gamification.marketguards.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
    override val layout: Int = R.layout.activity_login

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        viewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login_login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                login_login.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                login_password.error = getString(loginState.passwordError)
            }
        })

        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

//            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null && loginResult.success == true) {
                rootToDashboard()
            }
            setResult(Activity.RESULT_OK)
        })

        login_button.setOnClickListener {
            viewModel.login(login_login.text.toString(), login_password.text.toString())
        }

        viewModel.onCreate()
    }

    private fun rootToDashboard() {
        startActivity(MainActivity.createIntent(this))
        finish()
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}