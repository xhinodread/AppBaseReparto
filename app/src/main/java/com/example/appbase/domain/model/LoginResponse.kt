package com.example.appbase.domain.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("resp") val resp: RespuestaSegDespApi,
)
