package com.example.appbase.ui.fragment.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appbase.R
import com.example.appbase.ui.common.ConeccionStado
import com.example.appbase.ui.common.ConnectivityStatus
import com.example.appbase.ui.nav.NavigationItem
import com.example.appbase.ui.theme.Cremita
import com.example.appbase.ui.viewmodel.ConeccionViewModel
import com.example.appbase.ui.viewmodel.LoginViewModel
import com.example.appbase.utils.ConnectionState
import com.example.appbase.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun HomeScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    coneccionViewModel: ConeccionViewModel = hiltViewModel(),
) {
    //val loginViewModel: LoginViewModel = viewModel()

    val coneccionStatus by coneccionViewModel.isConected.observeAsState()

    val loginUiState by loginViewModel.uiState.observeAsState()
    Log.d("onclick", "HomeScreen: " + loginUiState.toString())

    val email :String by loginViewModel.email.observeAsState(initial="")

    val nombreDelUsuario =  if (loginUiState?.emailUser.isNullOrEmpty()) "Anonimo" else loginUiState?.emailUser

    /****
    Log.d("onCreate", "home coneccionStatus: " + coneccionStatus )
    LaunchedEffect(key1 = Unit) {
        if(coneccionStatus == false){
            //  coneccionViewModel.limpiarStatus()
            coneccionViewModel.statusConeccion()
        }
    }
    *****/

    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        text = "Usuario: ${nombreDelUsuario}, ${loginUiState?.rutUser} ",
        Text(
            text = "Usuario: ${nombreDelUsuario} ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(5.dp, 0.dp),
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
        //ConeccionStado(coneccionStatus == true)
        Text(
            text = "Transportes Piero",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(15.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Image(
            painterResource(
                id = R.drawable.camion1home
            ),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "¿Que deseas hacer hoy?",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .padding(5.dp)
                .background(Cremita),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.cajaseguimiento),
                contentDescription = stringResource(id = R.string.app_title),
               // contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(10.dp)
                    .size(174.dp, 174.dp)
                    .clickable {
                        navController.navigate(NavigationItem.Seguimiento.route)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.cajapago),
                contentDescription = stringResource(id = R.string.app_title),
               // contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp, 200.dp)
                    .clickable {
                        navController.navigate(NavigationItem.Pago.route)
                    }
            )
          }
        }
    }
}

