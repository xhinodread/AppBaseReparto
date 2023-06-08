package com.example.appbase.ui.fragment.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appbase.ui.viewmodel.SeguimientoViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

//@androidx.camera.core.ExperimentalGetImage
//@ExperimentalFoundationApi
@Composable
fun MyCuenta(
    seguimientoViewModel: SeguimientoViewModel = hiltViewModel()
){
    //Text(text = "My cuenta")
   // Text(text = "My nombre")
    var code by remember { mutableStateOf("") }
    val context = LocalContext.current
    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->

            if(result.contents != null){
                Log.d("scann", "scanned code: ${result.contents}")
                //Toast.makeText(context, result.contents, Toast.LENGTH_SHORT).show()
                code = result.contents
                seguimientoViewModel.addNrofolio(result.contents)

                clipboardManager.setText(AnnotatedString(( result.contents)))

            }else{
                Toast.makeText(context, "CANCELADO...", Toast.LENGTH_SHORT).show()
            }

        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        val scanOptions = ScanOptions()
        scanOptions.setOrientationLocked(true)

        Button(
            onClick = { scanLauncher.launch(scanOptions) }
        ){
            Text(text = "Scan barcode")
        }
        Text(text = "Lectura Codigo")
        Text(text = code)


    }
}