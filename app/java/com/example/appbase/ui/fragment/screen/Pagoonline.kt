package com.example.appbase.ui.fragment.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbase.R
import com.example.appbase.data.EstadoEnvio
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Pagoonline(){

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
            backgroundColor=(colorResource(id = R.color.colorPrimaryPi)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(5.dp)
                .clickable { focusRequesterMio.requestFocus() }
        ) {
            Image(painterResource(
                id = R.drawable.retiro_y_despacho_express),
                contentDescription = null,
                alignment = Alignment.BottomCenter
            )
            Column(modifier = Modifier.padding(10.dp)){
                Text(
                    text="PAGO EN LINEA",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text="Ingrese el Rut asociado al pago.",
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
                        label = {Text("Rut del cliente")},
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
                        colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.purple_pi)),
                        modifier = Modifier.padding(5.dp).fillMaxWidth()
                    ) {
                        Text(text="Hacer pago", color=Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
            }
        }
    }
}

private fun hacerClick(textoMensaje: String) {
    Log.e("onList", "Data Form Pago OnLine: " )
    Log.e("onList", textoMensaje )
}
