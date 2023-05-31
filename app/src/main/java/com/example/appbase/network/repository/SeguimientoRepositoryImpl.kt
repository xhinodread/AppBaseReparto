package com.example.appbase.network.repository

import android.util.Log
import com.example.appbase.domain.model.SeguimientoResponse
import com.example.appbase.domain.repository.SeguimientoRepository
import com.example.appbase.network.service.SeguimientoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody.Companion.asResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeguimientoRepositoryImpl @Inject constructor(
    private val seguimientoService: SeguimientoService,
    private val apiKey2: String,
 ) : SeguimientoRepository{

    override suspend fun getEstadoRed(): Boolean {
        return withContext(Dispatchers.IO) {
            val response = seguimientoService.getStatusRedYApi(apiKey2).onSuccess { cuerpo ->
                cuerpo.source()
            }.onFailure {
                it.message
            }
            response.isSuccess
        }
    }

    override suspend fun getEstadoCarga(nro_folio:String): SeguimientoResponse {
        val responseApi = seguimientoService.getEstadoCarga(key = apiKey2, nro_folio)
        return responseApi
    }

}
