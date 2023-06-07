package com.example.appbase.network.repository

import android.util.Log
import com.example.appbase.domain.repository.LoginRepository
import com.example.appbase.network.service.SeguimientoService
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.nio.Buffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val seguimientoService: SeguimientoService,
): LoginRepository{

    override suspend fun hacerLogin(user:String, pass: String): String {
        val jsonObject = JSONObject()
        jsonObject.put("user", user)
        jsonObject.put("pass", pass)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        /****
        val body = requestBody
        val buffer = okio.Buffer()
        body.writeTo(buffer)
        val bodyString = buffer.readUtf8()
        Log.d("onClick", "requestBody: "+ bodyString)
        *****/

      //  val responseApi = seguimientoService.login("chileregion" ,"abcde123")
        val responseApi2 = seguimientoService.login2(requestBody)

        /*** val prettyJson = gson.toJson(
            JsonParser.parseString(
                responseApi.body()
                    ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
            )
        )
        ***/
        /****
        if(responseApi.isSuccessful){
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(
                JsonParser.parseString(
                    responseApi.body()
                        ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                )
            )
            Log.d("onClick", "->"+ prettyJson.toString())
        }
        ***/
        if(responseApi2.isSuccessful){
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(
                JsonParser.parseString(
                    responseApi2.body()
                        ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                )
            )
            //Log.d("onClick", "2 prettyJson ->"+ prettyJson.toString() )
            //Log.d("onClick", "2 prettyJson ->"+ (prettyJson == "null") )
            if( prettyJson == "null" ){
                return ""
            }
            val api_key = gson.fromJson<Respo>(prettyJson, Respo::class.java)

          //  Log.d("onClick", "2 ->"+ api_key.resp )
           // return prettyJson
            return api_key.resp.toString()
        }
     //   Log.d("onClick", responseApi.headers().toString())

       return ""
    }

}

data class Respo(var resp:String?)