package com.example.appbase.network.service

import android.util.Log
import com.example.appbase.domain.model.SeguimientoResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
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

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
       @Field("user") user:String,
       @Field("pass") pass:String
   ): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login2(@Body requestBody: RequestBody): Response<ResponseBody>

}
