package com.example.appbase.ui.fragment.alert

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.appbase.R
import com.example.appbase.ui.theme.Cremita
import com.example.appbase.ui.theme.Purple200

@Composable
fun SimpleAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    Log.d("onClick", show.toString())
    if (show) {
        AlertDialog(
            onDismissRequest =  onDismiss ,
            confirmButton = {
                Button(onClick =  onConfirm )
                { Text(text = "OK") }
            },
            dismissButton = {
                Button(onClick = onDismiss)
                { Text(text = "Cancel") }
            },
            title = { Text(text = "Please confirm") },
            text = { Text(text = "Should I continue with the requested action?") }
        )
    }
}

@Composable
fun SimpleAlertDialog2(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest =  onDismiss ,
            confirmButton = {
                Button(onClick =  onConfirm )
                { Text(text = "OK") }
            },
            dismissButton = {
                Button(onClick = onDismiss)
                { Text(text = "Cancel") }
            },
            title = { cabezaDialog() },
            text = { cuerpoDialog() },
            backgroundColor = Cremita,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun cuerpoDialog(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple200)
    ) {
        Text(text = "Aplicacion de seguimiento")
        Text(text = "por chileregion@gmail.com")

    }
}

@Composable 
fun cabezaDialog(){
    Image(
       painter = painterResource(id = R.drawable.logo_chileregion),
        contentDescription = null
    )

}
