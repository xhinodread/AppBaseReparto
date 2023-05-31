package com.example.appbase.ui.fragment.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbase.R
import com.example.appbase.data.EstadoEnvio
import com.example.appbase.ui.common.bordeBoton
import com.example.appbase.ui.viewmodel.LoginViewModel
import com.example.appbase.ui.viewmodel.SeguimientoViewModel

@Composable
fun Seguimiento(){

    val focusRequesterMio = remember { FocusRequester() }
    var texto by remember {mutableStateOf("") }
    var cpntt by remember {mutableStateOf(0) }

    val listEstado = listOf(
        EstadoEnvio("Ayer", "En Sucursal"),
        EstadoEnvio("Hoy", "En Ruta"),
        EstadoEnvio("Recien", "En Destino"),
        EstadoEnvio("Ahora", "En Reparto"),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        /****
        Text(
            text = "Seguimiento de encomiendas",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        *****/
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
                        onValueChange = {texto = it},
                        label = {Text("Nro de orden", color=Color.White)},
                        maxLines = 1,
                        modifier = Modifier
                            .width(200.dp)
                            .focusRequester(focusRequesterMio),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor =Color.White,
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            cursorColor = Color.Red
                        ),
                    )
                    Button(
                        onClick = { hacerClick(texto) },
                        colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.colorPrimaryDarkPi)),
                        modifier = Modifier.padding(5.dp).fillMaxWidth()
                    ) {
                        Text(text="Rastrear", color=Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                ){
                    listEstado.map { estadoEnvio->
                        var texto = "${estadoEnvio.fecha} ${estadoEnvio.estado}"
                       // FormaTrackingC(shape= CircleShape, texto)
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                //EncomiendaEntregada()
            }
        }
    }
}


private fun hacerClick(texto: String) {
    Log.e("onList", "Data Form Seguimiento: " )
    Log.e("onList", texto )
}


@Composable
fun FormaTrackingC(shape: Shape, texto:String){
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(shape)
            .background(colorResource(id = R.color.rojo_pi))
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text=texto,
            fontSize = 15.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold
        )
    }
    Spacer(modifier = Modifier.width(35.dp))
}

@Composable
fun EncomiendaEntregada(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.rojo_pi))
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Encomienda Entregada al cliente",
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

