package com.example.appbase.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.appbase.domain.usecase.GetEstadoApiUseCase
import com.example.appbase.domain.usecase.HacerLoginUseCase
import com.example.appbase.network.repository.Respo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("CAST_NEVER_SUCCEEDS")
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getEstadoApiUseCase: GetEstadoApiUseCase,
    private val hacerLoginUseCase: HacerLoginUseCase,
) : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean>(true)
    val isLogin : LiveData<Boolean> =_isLogin

    private val _tokenLogin = MutableLiveData<String>()
    val tokenLogin: LiveData<String> = _tokenLogin

    private val _isConected = MutableLiveData<Boolean>()
    val isConected : MutableLiveData<Boolean> =_isConected

    private val _uiState = MutableLiveData<UserUiState>()
    var uiState: LiveData<UserUiState> = _uiState

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _msgSucces = MutableLiveData<String>("")
    val msgSucces: LiveData<String> = _msgSucces

    private val _isEnabled = MutableLiveData(true)
    val isEnabled : LiveData<Boolean> =_isEnabled


    fun statusConeccion(){
        viewModelScope.launch {
            val result = getEstadoApiUseCase()
            isConected.postValue(result)
        }
    }
    fun setEnables() {
        _isEnabled.value = isEnabled.value
    }

    fun vaciarMsg() = _msgSucces.postValue("")

    fun userUiState(elEmail:String, laPassw: String) {
        if(isValidEmail(elEmail) && isValidPass(laPassw) ){
            // Log.d("onclick", elEmail)
            _uiState.value = UserUiState(emailUser = elEmail, passUser = laPassw, rutUser= "11.111.111-1")
            hacerLogin(elEmail, laPassw)
            //_email.value = elEmail
        }else{
            _msgSucces.value = "Revise su nombre de usuario y contraseña"
        }
    }

    private fun hacerLogin(elEmail:String, laPassw: String){
        viewModelScope.launch {
            _isEnabled.value = false
            // val login = hacerLoginUseCase("chileregion", "abcde123")
            val login = hacerLoginUseCase(elEmail, laPassw)
            Log.d("onClick", "login:" + login)
            Log.d("onClick", "login:" + login.length)
            _msgSucces.postValue("")
            if( login.length <=0 ){
               // Log.d("onClick", "Rut o clave incorrecto:" + login)

                // _msgSucces.value = "Rut o clave incorrectos......"
                _msgSucces.postValue("Rut o clave incorrectos......")
                _isLogin.value = true
            }else{
               // _msgSucces.value = "Success......"
                _msgSucces.postValue("Success......")
                _isLogin.value = false
                _tokenLogin.value = login
            }
            _isEnabled.value = true
        }
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
