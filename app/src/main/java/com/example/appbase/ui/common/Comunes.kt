package com.example.appbase.ui.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import com.example.appbase.data.EstadoEnvio


var bordeBoton = RoundedCornerShape(50)

fun listaRand(largoLista: Int): Int {
    return (0 until largoLista+1).random()
}

fun listadoHistoria(): List<EstadoEnvio> {
    //val fechas = listOf("Ayer", "Hoy", "Recien", "Ahora", "Encomienda")
    val fechas = listOf("22/05/2023", "23/05/2023", "24/05/2023", "24/05/2023", "24/05/2023")
    val etiquetas = listOf("En Sucursal", "En Ruta", "En Destino", "En Reparto", "Encomienda Entregada al cliente")
    var elEstado = "1"
    var sw = 0
    val miLista = fechas.mapIndexed { ind, valor ->
        elEstado = estadoHistoria(elEstado.toInt(), sw)
        sw = 1
        //EstadoEnvio(ind.toString(), valor, etiquetas[ind]+'\n' +elEstado, elEstado)
        EstadoEnvio(ind.toString(), valor, etiquetas[ind], elEstado)
    }
    return miLista
}
fun estadoHistoria(valorInicial:Int, sw:Int): String{
    //Log.d("onList","valorInicial"+ '\n'+  valorInicial )
    var resultadoEstado = valorInicial.toString()
    if(sw == 1) if (valorInicial == 1) resultadoEstado = listaRand(1).toString()
    return resultadoEstado
}

/***
fun estadoDeLaRed(): Boolean{
 /***
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
***/
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

    return isConnected
}
***/

@Composable
fun myAppTextFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
    textColor = Color.Black,
    focusedBorderColor = Color.LightGray,
    unfocusedBorderColor = Color.Black,
    focusedLabelColor = Color.Black,
    cursorColor = Color.Red
)



var cuandoTengaErrorElIde = "rm -rf android/.gradle" + "rm -rf .gradle" + "rm -rf ~/.gradle"+"rm -rf ~/.gradle/caches"+
"./gradlew cleanBuildCache"
