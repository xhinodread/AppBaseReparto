package com.example.appbase.domain.usecase

import android.util.Log
import com.example.appbase.domain.model.LoginResponse
import com.example.appbase.domain.repository.LoginRepository
import com.example.appbase.network.repository.Respo
import javax.inject.Inject

@Suppress("CAST_NEVER_SUCCEEDS")
class HacerLoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user:String, pass: String): String {
        // Log.d("onClick", "invoke: $user ,  $pass" )
        val getEstadoCarga = repository.hacerLogin(user, pass)
        return getEstadoCarga
    }
}