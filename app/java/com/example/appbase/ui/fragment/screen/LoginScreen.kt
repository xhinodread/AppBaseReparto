package com.example.appbase.ui.fragment.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appbase.R
import com.example.appbase.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onClickAction: (String)->Unit,
    loginViewModel: LoginViewModel
){

   // val loginViewModel: LoginViewModel = viewModel()

    ///var emailUser by remember { mutableStateOf(emailUser) }
    var emailUser by remember { mutableStateOf("") }
    var passwUser by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Image(painter = painterResource(id=R.drawable.camion1home), contentDescription = null)
        ImputStd(emailUser, {emailUser = it}, "Email usuario"  )
        ImputStd(passwUser, {passwUser = it}, "Password"  )

        Button(
            onClick = {
                loginViewModel.userUiState(emailUser, passwUser)
                clickLogin(onClickAction, emailUser, passwUser)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor= colorResource(id = R.color.colorPrimaryDarkPi)),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text(text="Login", color= Color.White)
        }
    }
}

fun clickLogin(onClickAction: (String) -> Unit, emailUser: String, passwUser: String){

    onClickAction(emailUser)
}


@Composable
fun ImputStd(
    value: String = "",
    onValueChanged: ((value: String) -> Unit)? = null,
    label: String? = ""
){
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged?.invoke(it) },
        label = {
            if (label != null) {
                Text(text=label)
            }
        },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor =Color.Black,
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Red
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