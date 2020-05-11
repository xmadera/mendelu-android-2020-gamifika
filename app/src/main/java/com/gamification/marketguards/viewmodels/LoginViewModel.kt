package com.gamification.marketguards.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamification.marketguards.R
import com.gamification.marketguards.data.model.auth.LoginResult
import com.gamification.marketguards.data.database.repository.ILoginRepository
import com.gamification.marketguards.data.network.communication.LoginRESTApiRepositoryImpl
import com.gamification.marketguards.ui.login.LoginFormState

class LoginViewModel(private val loginRepository: LoginRESTApiRepositoryImpl) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun onCreate() {
        loginRepository.successfullyLoggedIn.observeForever {
            _loginResult.value = it
        }
    }

    fun login(login: String, password: String) {
        loginRepository.login(login, password)
    }

    fun loginDataChanged(login: String, password: String) {
        if (!isUserNameValid(login)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(login: String): Boolean {
        return if (login.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(login).matches()
        } else {
            login.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}