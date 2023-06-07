package com.example.appbase.ui.fragment.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.appbase.R
import com.example.appbase.ui.common.bordeBoton
import com.example.appbase.ui.common.myAppTextFieldColors
import com.example.appbase.ui.theme.Cremita
import com.example.appbase.ui.viewmodel.LoginViewModel
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appbase.core.ConnectionState
import com.example.appbase.core.connectivityState
import com.example.appbase.ui.clickLogin
import com.example.appbase.ui.common.ConeccionStado
import com.example.appbase.ui.common.ConnectivityStatus
import com.example.appbase.ui.viewmodel.ConeccionViewModel

@Composable
fun LoginScreen(
    onClickAction: (String)->Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    coneccionViewModel: ConeccionViewModel = hiltViewModel()
){

    var emailUser by remember { mutableStateOf("chileregion@gmail.com") }
    var passwUser by remember { mutableStateOf("abcde123") }
    var estadoBoton by remember { mutableStateOf(true) }

    val coneccionStatus by coneccionViewModel.isConected.observeAsState()

    val context = LocalContext.current

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    LaunchedEffect(key1 = Unit) {
        coneccionViewModel.statusConeccion()
    }

    Box(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id=R.drawable.camion1home_toon),
            contentDescription = "Login Imagen",
            contentScale = ContentScale.FillBounds ,
            modifier = Modifier.fillMaxWidth(),
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){

            ConstraintLayout {
                val (surface, fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Cremita,
                    shape = RoundedCornerShape(
                        topStartPercent = 2,
                        topEndPercent = 2
                    )
                ){
                    Column() {
                        Text(
                            text="Bienvenido a Transportes Piero",
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text="Escriba su cuenta email y contraseña para ingresar",
                            color = MaterialTheme.colors.secondaryVariant
                            //style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.secondaryVariant)
                        )
                        ImputStd(emailUser, {emailUser = it}, "Email usuario"  )
                        ImputStd(passwUser, {passwUser = it}, "Password"  )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            enabled=estadoBoton,
                            onClick = {
                                estadoBoton=false
                                coneccionViewModel.statusConeccion()
                                if( coneccionStatus == true ){
                                    Log.d("onClick", "B "+estadoBoton.toString())
                                    if( emailUser.isNullOrEmpty() || passwUser.isNullOrEmpty() ){
                                        Toast.makeText(context, "Campo vacio", Toast.LENGTH_SHORT).show()
                                    }else{
                                        loginViewModel.userUiState(emailUser, passwUser)
                                        clickLogin(onClickAction, emailUser, passwUser)
                                    }
                                }
                                estadoBoton=true
                            },
                         //   colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.colorPrimaryDarkPi)),
                            colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = colorBotonLogin(estadoBoton))),
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = bordeBoton
                        ) {
                            Text(text="Login", color= Color.White)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row( modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                            .padding(5.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(text="Olvide mi contraseña",
                                color= Color.Black,
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle(1) ,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "Recordar contraseña",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            )
                        }
                        // Spacer(modifier = Modifier.height(20.dp))

                        ConeccionStado(coneccionStatus == true)
                        ConnectivityStatus()
                        Spacer(modifier = Modifier.height(25.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.capturahomepiero),
                                contentDescription = stringResource(id = R.string.app_title),
                                alignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth().size(300.dp)
                            )
                        }

                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .size(120.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-55).dp)
                            end.linkTo(surface.end, margin = -5.dp)
                        },
                    backgroundColor = Color.Transparent,
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.logosolop),
                        contentDescription = "logosolop",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }


    }
}

fun clickLogin(onClickAction: (String) -> Unit, emailUser: String, passwUser: String){
    onClickAction(emailUser)
}

fun colorBotonLogin(estadoBoton: Boolean): Int{
    return if(estadoBoton) R.color.colorPrimaryDarkPi else R.color.teal_200
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImputStd(
    value: String = "",
    onValueChanged: ((value: String) -> Unit)? = null,
    label: String? = ""
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged?.invoke(it) },
        label = {
            if (label != null) {
                Text(text=label, color=Color.Black)
            }
        },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        colors = myAppTextFieldColors(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()},
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

/*****
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen("",{})
}


 *
 *
 *
 OutlinedTextField(
value = emailUser,
onValueChange = {emailUser = it},
label = {Text("Email usuario")},
maxLines = 1,
modifier = Modifier
.fillMaxWidth(),
keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
colors = TextFieldDefaults.outlinedTextFieldColors(
textColor =Color.White,
focusedBorderColor = Color.LightGray,
unfocusedBorderColor = Color.White,
focusedLabelColor = Color.White,
cursorColor = Color.Red
)
)
 *
 *
 */