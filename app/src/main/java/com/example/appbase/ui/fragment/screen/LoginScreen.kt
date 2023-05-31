package com.example.appbase.ui.fragment.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appbase.ui.common.ConeccionStado
import com.example.appbase.ui.common.ConnectivityStatus
import com.example.appbase.ui.viewmodel.ConeccionViewModel

@Composable
fun LoginScreen(
    onClickAction: (String)->Unit,
    loginViewModel: LoginViewModel,
    coneccionViewModel: ConeccionViewModel = hiltViewModel()
){

    var emailUser by remember { mutableStateOf("") }
    var passwUser by remember { mutableStateOf("") }

    val coneccionStatus by coneccionViewModel.isConected.observeAsState()


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
                            onClick = {
                                coneccionViewModel.statusConeccion()
                                Log.d("onCreate", "coneccionStatus: $coneccionStatus")
                                if( coneccionStatus == true ){
                                    loginViewModel.userUiState(emailUser, passwUser)
                                    clickLogin(onClickAction, emailUser, passwUser)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.colorPrimaryDarkPi)),
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = bordeBoton
                        ) {
                            Text(text="Login", color= Color.White)
                        }
                        ConeccionStado(coneccionStatus == true)
                        ConnectivityStatus()
                        Spacer(modifier = Modifier.height(25.dp))
                        Image(
                            painter = painterResource(id = R.drawable.capturahomepiero),
                            contentDescription = stringResource(id = R.string.app_title),
                            alignment = Alignment.Center
                        )
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