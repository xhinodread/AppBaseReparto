package com.example.appbase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.appbase.domain.usecase.GetEstadoApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getEstadoApiUseCase: GetEstadoApiUseCase
) : ViewModel() {

    private val _isConected = MutableLiveData<Boolean>()
    val isConected : MutableLiveData<Boolean> =_isConected

    private val _uiState = MutableLiveData<UserUiState>()
    var uiState: LiveData<UserUiState> = _uiState

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    fun statusConeccion(){
        viewModelScope.launch {
            val result = getEstadoApiUseCase()
            isConected.postValue(result)
        }
    }

    fun userUiState(elEmail:String, laPassw: String) {
        // Log.d("onclick", elEmail)
        _uiState.value = UserUiState(emailUser = elEmail, passUser = laPassw, rutUser= "11.111.111-1")
        //_email.value = elEmail
    }

    private fun isValidEmail(email:String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPass(password: String):Boolean = password.length > 6
}

data class UserUiState(
    val emailUser: String = "",
    val passUser: String = "",
    val rutUser: String = "",
    val tokenApi: String = ""

)
