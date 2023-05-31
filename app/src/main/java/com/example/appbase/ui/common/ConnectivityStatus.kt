package com.example.appbase.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appbase.ui.viewmodel.LoginViewModel
import com.example.appbase.utils.ConnectionState
import com.example.appbase.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus(
) {
    // This will cause re-composition on every network state change
    val connection by connectivityState()
    //val coneccionStatus by loginViewModel.isConected.observeAsState()

    val isConnected = connection === ConnectionState.Available

    // loginViewModel.statusApi()

    /****
    if (isConnected) {
        Text("CONECTADO")
    } else {
        Text("SIN CONEXION......")
    }
    *****/
    Column(modifier = Modifier.padding(2.dp)) {
    //    Text("conec: "+ coneccionStatus )
        if (isConnected == false) {
            Text(" SIN CONEXION......")
        }
    }
}