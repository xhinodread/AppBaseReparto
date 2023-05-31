package com.example.appbase.domain.model

import com.google.gson.annotations.SerializedName

data class SeguimientoResponse(
    @SerializedName("resp") val resp: RespuestaSegDespApi,
)

data class RespuestaSegDespApi(
    val nroPedido: String,
    @SerializedName("datos") val recorrido : List<Despacho>
)

data class Despacho(
    val id: String = "",
    val fecha:String = "",
    val mensaje:String = "",
    val estado:String = ""
)
