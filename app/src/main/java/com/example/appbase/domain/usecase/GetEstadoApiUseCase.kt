package com.example.appbase.domain.usecase

import com.example.appbase.domain.repository.SeguimientoRepository
import javax.inject.Inject

class GetEstadoApiUseCase @Inject constructor(
    private val repository: SeguimientoRepository
){
    suspend operator fun invoke():Boolean{
        val getEstado = repository.getEstadoRed()
        return getEstado
    }
}