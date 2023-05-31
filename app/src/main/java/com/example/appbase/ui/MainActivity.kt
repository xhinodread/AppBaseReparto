package com.example.appbase.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appbase.R
import com.example.appbase.ui.fragment.screen.LoginScreen
import com.example.appbase.ui.nav.BottomNavigationBar
import com.example.appbase.ui.nav.Navigation
import com.example.appbase.ui.nav.TopBar
import com.example.appbase.ui.theme.AppBaseTheme
import com.example.appbase.ui.theme.Cremita
import com.example.appbase.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBaseTheme{
                MainScreen()
            }
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }
}

@Composable
fun MainScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    var statusPantalla by remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val context = LocalContext.current

     if(statusPantalla){
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .background(Cremita)
         ){
           // loginViewModel.statusApi()
             LoginScreen(
                onClickAction = { statusPantalla = clickLogin(context, statusPantalla) }  ,
                loginViewModel
             )
         }
     }else{
         Scaffold(
             topBar = { TopBar() },
             bottomBar = { BottomNavigationBar(navController) },
             content = { padding ->
                 Box(modifier = Modifier.padding(padding)) {
                     Navigation(navController = navController, loginViewModel)
                 }
             },
             backgroundColor = Cremita // Set background color to avoid the white flashing when you switch between screens
         )
     }
}


fun clickLogin(context: Context? = null, statusPantalla: Boolean): Boolean {
    Toast.makeText(context, "Success......", Toast.LENGTH_SHORT ).show()
    return !statusPantalla
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    // BottomNavigationBar()
}