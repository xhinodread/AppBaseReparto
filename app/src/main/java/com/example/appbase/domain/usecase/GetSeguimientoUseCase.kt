package com.example.appbase.domain.usecase

import com.example.appbase.domain.model.SeguimientoResponse
import com.example.appbase.domain.repository.SeguimientoRepository
import javax.inject.Inject

class GetSeguimientoUseCase @Inject constructor(
    private val repository: SeguimientoRepository
) {
    suspend operator fun invoke(nro_folio:String): SeguimientoResponse {
        val getEstadoCarga = repository.getEstadoCarga(nro_folio)
        return getEstadoCarga
    }
}