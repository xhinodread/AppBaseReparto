package com.example.appbase.ui.fragment.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appbase.R
import com.example.appbase.ui.common.bordeBoton
import com.example.appbase.ui.common.myAppTextFieldColors
import com.example.appbase.ui.viewmodel.SeguimientoViewModel
import com.example.appbase.ui.viewmodel.WhatAppViewModel

@Composable
fun Cotizacion(){

    val focusRequesterMio = remember { FocusRequester() }
    var texto by remember {mutableStateOf("") }
    var cpntt by remember {mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Card(
            elevation = 4.dp,
            backgroundColor=(colorResource(id = R.color.white)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            /****
            Image(painterResource(
                id = R.drawable.retiro_y_despacho_express),
                contentDescription = null,
                alignment = Alignment.BottomCenter
            )
            *****/
            Column(modifier = Modifier.padding(10.dp)){
                Text(
                    text="FORMULARIO DE COTIZACIÓN",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text="Retiro, envio y entrega de encomiendas y documentos.",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(5.dp)
                )
                FrmCotizacion()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FrmCotizacion(whatAppViewModel: WhatAppViewModel = hiltViewModel()){

    var textoMensaje by remember {mutableStateOf("") }
    val formState = remember { mutableStateMapOf<String, String>() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val formFields = listOf(
        FormField(label="textoOrigen",value="", titulo = "Origen", type = FormType.TEXT),
        FormField(label="textoDestino", value="", titulo = "Destino", type = FormType.TEXT),
        FormField(label="textoTipodeCarga", value="", titulo = "Tipo de Carga", type = FormType.TEXT),
        FormField(label="textoCantidad", value="", titulo = "Cantidad", type = FormType.TEXT),
        FormField(label="textoPeso", value="", titulo = "Peso", type = FormType.TEXT),
        FormField(label="textoDimensiones", value="", titulo = "Dimensiones (Largo x Ancho x Alto)", type = FormType.TEXT),
    )

   Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        formFields.forEach { formField ->
            OutlinedTextField(
                value = formState[formField.label] ?: formField.value,
                onValueChange = {formState[formField.label] = it},
                label = {Text(text=formField.titulo, color=Color.Black)},
                maxLines = 1,
                singleLine = true,
                colors = myAppTextFieldColors(),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()},
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }
        OutlinedTextField(
            value = textoMensaje,
            onValueChange = {textoMensaje = it},
            label = {Text("Mensaje", color=Color.Black)},
            maxLines = 5,
            colors = myAppTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = { hacerClick(formState, textoMensaje,whatAppViewModel, context) },
            colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.purple_pi)),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = bordeBoton
        ) {
            Text(text="Enviar cotizacion", color=Color.White)
        }
    }
}

private fun hacerClick(
    formState: SnapshotStateMap<String, String>,
    textoMensaje: String,
    whatAppViewModel: WhatAppViewModel,
    unContext: Context
    ) {
  //  formState.toMap()
    Log.e("onList", "Data Form: " )
    Log.e("onList", formState.toMap().toString() )
    Log.e("onList", textoMensaje )

    whatAppViewModel.apply {
     if(true) enviarPedidoWhatApp(unContext)
    }
}


enum class FormType{
    TEXT,
    EMAIL,
    PASSWORD
}
data class FormField(var label:String, var type:FormType, var value:String="", var titulo:String="")

@Composable
fun CamposFijosFormulario(){
/*****
    OutlinedTextField(
        value = textoOrigen,
        onValueChange = {textoOrigen = it},
        label = {Text("Origen")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = textoDestino,
        onValueChange = {textoDestino = it},
        label = {Text("Destino")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = textoTipodeCarga,
        onValueChange = {textoTipodeCarga = it},
        label = {Text("Tipo de Carga")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = textoCantidad,
        onValueChange = {textoCantidad = it},
        label = {Text("Cantidad")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = textoPeso,
        onValueChange = {textoPeso = it},
        label = {Text("Peso")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = textoDimensiones,
        onValueChange = {textoDimensiones = it},
        label = {Text("Dimensiones (Largo x Ancho x Alto)")},
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
****/
}