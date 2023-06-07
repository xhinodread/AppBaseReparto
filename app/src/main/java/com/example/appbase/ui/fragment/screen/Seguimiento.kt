package com.example.appbase.ui.fragment.screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.map
import com.example.appbase.R
import com.example.appbase.data.EstadoEnvio
import com.example.appbase.ui.common.bordeBoton
//import com.example.appbase.ui.common.estadoDeLaRed
import com.example.appbase.ui.theme.Cremita
import com.example.appbase.ui.viewmodel.ConeccionViewModel
import com.example.appbase.ui.viewmodel.LoginViewModel
import com.example.appbase.ui.viewmodel.SeguimientoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Seguimiento(
    seguimientoViewModel: SeguimientoViewModel = hiltViewModel(),
    coneccionViewModel: ConeccionViewModel = hiltViewModel(),
){
    val focusRequesterMio = remember { FocusRequester() }
    var texto by remember {mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val listaEstadoDespacho by seguimientoViewModel.estadoDespacho.observeAsState()
    val cargandoDespacho by seguimientoViewModel.cargando.observeAsState()
    var swFinal = 0
    var textoMsg =""
    var textobusqueda =""

    val connectionType = remember {mutableStateOf("Not Connected") }

    /*****
    LaunchedEffect(key1 = Unit) {
        seguimientoViewModel.limpiarLista()
        coneccionViewModel.statusConeccion()
    }
    *****/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Card(
            elevation = 4.dp,
            backgroundColor=(colorResource(id = R.color.purple_pi)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(5.dp)
                .clickable { focusRequesterMio.requestFocus() }
        ) {
            Image(painterResource(
                id = R.drawable.distribucion),
                contentDescription = null,
                alignment = Alignment.BottomCenter
            )
            Column(modifier = Modifier.padding(10.dp)){
                Text(
                    text="SEGUIMIENTO EN LINEA",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text="Para buscar, ingrese su número de orden de flete.",
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    OutlinedTextField(
                        value = texto,
                        onValueChange = {
                            texto = texto.replace("\\s+".toRegex(),"" )
                            texto = texto.replace(".","" )
                            texto = texto.replace(",","" )
                            texto = texto.replace("-","" )
                            if (it.length <= 10) {
                                texto = it
                            }
                        },
                        label = {Text("Nro de orden", color=Color.White)},
                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier
                            .width(200.dp)
                            .focusRequester(focusRequesterMio),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor =Color.White,
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            cursorColor = Color.Red
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()},
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                    Button(
                        onClick = {
                            /******* VER RED *******/
                            /***
                            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                            // Fetching the Network Information
                            val netInfo = cm.allNetworkInfo
                            // on below line finding if connection
                            // type is wifi or mobile data.
                            for (ni in netInfo) {
                                if (ni.typeName.equals("WIFI", ignoreCase = true))
                                    if (ni.isConnected) connectionType.value = "WIFI"
                                if (ni.typeName.equals("MOBILE", ignoreCase = true))
                                    if (ni.isConnected) connectionType.value = "MOBILE DATA"
                            }
                            ****/
                            Red(context, connectionType)
                            //Log.d("onList", texto)
                            texto = texto.replace("\\s+".toRegex(),"" )
                            texto = texto.replace(".","" )
                            texto = texto.replace(",","" )
                            texto = texto.replace("-","" )
                            if( texto.isEmpty() || texto == "0" || texto.toLong()<=0 ){
                                Toast.makeText(context, "Numero de Order no valido", Toast.LENGTH_SHORT ).show()
                            }else{
                                keyboardController?.hide()
                                hacerClick(texto, seguimientoViewModel)// Dispatchers.IO (main-safety block)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.colorPrimaryDarkPi)),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = bordeBoton,
                        enabled = cargandoDespacho == !true
                    ) {
                        Text(text="Rastrear", color=Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
                if(cargandoDespacho == true ){
                    textobusqueda="Sin data"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.size(100.dp),
                            color = Cremita,
                            strokeWidth = 10.dp
                        )
                    }
                }else {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        var estadoFinal = ""
                        var textoFinal = ""
                        var myModifier : Modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                        if (listaEstadoDespacho?.isNotEmpty() == true)myModifier = Modifier.fillMaxWidth()
                        Row(
                            modifier = myModifier,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            if (listaEstadoDespacho?.isNotEmpty() == true) {
                                listaEstadoDespacho?.mapIndexed { ind, estadoEnvio ->
                                    textoMsg = estadoEnvio.fecha+'\n'+estadoEnvio.mensaje
                                    if (estadoEnvio.id.toInt() < 4) FormaTracking(shape = CircleShape, textoMsg, estadoEnvio.estado)
                                    if (estadoEnvio.id.toInt() >= 4) {
                                        swFinal = 1
                                        estadoFinal = estadoEnvio.estado
                                        textoFinal=estadoEnvio.fecha+'\n'+estadoEnvio.mensaje
                                    }
                                }
                            }else{
                                Text(textobusqueda, color=Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        if (swFinal == 1) {
                            EncomiendaEntregada(estadoFinal, textoFinal)
                            swFinal = 0
                        }
                    }
                }
            }
        }
    }


}


//@Composable
fun Red(context: Context, connectionType:MutableState<String>){


        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

// Fetching the Network Information
    val netInfo = cm.allNetworkInfo
    val netInfoX = cm.activeNetwork
    val netInfoY = cm.activeNetwork

    // on below line finding if connection
    // type is wifi or mobile data.
    for (ni in netInfo) {
        if (ni.typeName.equals("WIFI", ignoreCase = true))
            if (ni.isConnected) connectionType.value = "WIFI"
        if (ni.typeName.equals("MOBILE", ignoreCase = true))
            if (ni.isConnected) connectionType.value = "MOBILE DATA"
    }
    Log.d("onList", "netInfoX \n"+ netInfoX)
    Log.d("onList", "connectionType \n"+ connectionType.value)
}

private fun hacerClick(texto: String, seguimientoViewModel: SeguimientoViewModel) {
    // Log.e("onList", "Data Form Seguimiento: " )
   // Log.e("onList", texto )
    seguimientoViewModel.getStadoDespacho(texto)
}


@Composable
fun FormaTracking(shape: Shape, texto:String, estadoTexto:String){
    val colorTexto = if(estadoTexto=="1")Color.White else Color.Gray
    val colorShape = if(estadoTexto=="1")R.color.rojo_pi else R.color.purple_pi
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(shape)
                .border(BorderStroke(2.dp, Color.Red))
                .background(colorResource(id = colorShape))
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(1.dp)
        ){
            Text(
                text=texto,
                fontSize = 11.sp,
                color = colorTexto,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
        }
        if(estadoTexto=="1"){
            Text("-->", color=Color.Yellow)
        }else{
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}

@Composable
fun EncomiendaEntregada(estadoTexto:String ,textoFinal:String){
    val colorTexto = if(estadoTexto=="1")Color.White else Color.Gray
    val colorShape = if(estadoTexto=="1")R.color.rojo_pi else R.color.purple_pi
    val tamanioShape = if(estadoTexto=="1")180.dp else 150.dp
    val tamanioTexto = if(estadoTexto=="1")20.sp else 15.sp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(tamanioShape)
                .border(2.dp, Color.Red, CircleShape)
                .clip(CircleShape)
                .background(colorResource(id = colorShape))
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(5.dp)
        ) {
            Text(
                text = textoFinal,
                fontSize = tamanioTexto,
                color = colorTexto,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

