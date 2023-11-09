package com.alexis.composelogin.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexis.composelogin.domain.Login

class LoginViewModel : ViewModel() {

    private val _login = MutableLiveData<Login>()
    val login: LiveData<Login> = _login

    fun login(login: Login) {
        _login.value = login.copy(enableBottom = enableLogin(login))
    }

    private fun enableLogin(login: Login) =
        Patterns.EMAIL_ADDRESS.matcher(login.email).matches() && login.password.length > 6

}

