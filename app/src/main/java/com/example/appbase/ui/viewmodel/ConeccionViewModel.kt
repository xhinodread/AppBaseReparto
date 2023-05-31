package com.example.appbase.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbase.domain.usecase.GetEstadoApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConeccionViewModel @Inject constructor(
    private val getEstadoApiUseCase: GetEstadoApiUseCase
): ViewModel() {

    private val _isConected = MutableLiveData<Boolean>(false)
    val isConected : MutableLiveData<Boolean> =_isConected

    fun limpiarStatus(){
        isConected.postValue(true)
    }

    fun statusConeccion(){
        viewModelScope.launch {
            isConected.postValue(false)
            val result = getEstadoApiUseCase()
            isConected.postValue(result)
        }
    }

}