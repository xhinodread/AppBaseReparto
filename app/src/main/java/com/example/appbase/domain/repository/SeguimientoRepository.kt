package com.example.appbase.domain.repository

import com.example.appbase.domain.model.SeguimientoResponse

interface SeguimientoRepository {

    suspend fun getEstadoRed():Boolean

    suspend fun getEstadoCarga(nro_folio:String): SeguimientoResponse
}