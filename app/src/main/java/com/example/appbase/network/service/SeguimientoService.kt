package com.example.appbase.network.service

import com.example.appbase.domain.model.SeguimientoResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface SeguimientoService {

    @GET("index")
    suspend fun getStatusRedYApi(
        @Query("apiKey") key: String,
        @Query("nro_folio") nroFolio: String="0"
    ): Result<ResponseBody>

    @GET("seguimiento_app")
    suspend fun getEstadoCarga(
        @Query("apiKey") key: String,
        @Query("nro_folio") nroFolio: String
    ): SeguimientoResponse

}