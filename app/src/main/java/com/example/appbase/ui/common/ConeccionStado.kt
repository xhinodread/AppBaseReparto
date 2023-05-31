package com.example.appbase.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ConeccionStado(
    coneccionStatus: Boolean
) {
    Column(modifier = Modifier.padding(2.dp)) {
        if(coneccionStatus == false){
            Text("SIN CONECCION. Revise su red...", color = Color.Red)
        }
    }
}