package com.gamification.marketguards.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamification.marketguards.data.model.auth.LoginResult
import com.gamification.marketguards.data.database.repository.LoginRepositoryInterface
import com.gamification.marketguards.ui.login.LoginFormState

class LoginViewModel(private val loginRepository: LoginRepositoryInterface) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun onCreate() {
        loginRepository.successfullyLoggedIn.observeForever {
            _loginResult.value = it
        }
    }

    fun login(username: String, password: String) {
        loginRepository.login(username, password)
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}