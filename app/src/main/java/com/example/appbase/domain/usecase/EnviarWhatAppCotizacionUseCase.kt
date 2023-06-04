package com.example.appbase.domain.usecase

import android.content.Context
import com.example.appbase.domain.Estandard
import javax.inject.Inject

class EnviarWhatAppCotizacionUseCase@Inject constructor(
    private val estandard: Estandard
) {
    operator fun invoke(mensaje: String, context: Context){
        estandard.openWhatsAppSend(mensaje, context)
    }

}