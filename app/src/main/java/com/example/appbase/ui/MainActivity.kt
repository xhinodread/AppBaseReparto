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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appbase.R
import com.example.appbase.ui.fragment.screen.LoginScreen
import com.example.appbase.ui.fragment.screen.clickLogin
import com.example.appbase.ui.nav.BottomNavigationBar
import com.example.appbase.ui.nav.DrawerMenu
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
    }
}

@Composable
fun MainScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    var statusPantalla2 by remember { mutableStateOf(true) }
    val statusPantalla by loginViewModel.isLogin.observeAsState()
    val msgSucces by loginViewModel.msgSucces.observeAsState()


    val navController = rememberNavController()
    val estadoDrawer = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

     if(statusPantalla == true){
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .background(Cremita)
         ){
           // loginViewModel.statusApi()
             LoginScreen(
                onClickAction = { clickLogin(context, statusPantalla!!, msgSucces, loginViewModel) }
             )
         }
     }else{
         Scaffold(
             topBar = { TopBar(estadoDrawer) },
             bottomBar = { BottomNavigationBar(navController) },
             content = { padding ->
                 Box(modifier = Modifier.padding(padding)) {
                     Navigation(navController = navController, loginViewModel)
                 }
             },
             backgroundColor = Cremita, // Set background color to avoid the white flashing when you switch between screens
             scaffoldState = estadoDrawer,
             drawerContent = {
                 Column() {
                     Text(text = "Menú Cliente")
                     DrawerMenu(navController , coroutineScope  , estadoDrawer  )
                 }
             }
         )
     }
}


fun clickLogin(context: Context? = null, statusPantalla: Boolean, msgSucces:String?, loginViewModel: LoginViewModel): Boolean {
    if( msgSucces != "" )Toast.makeText(context, msgSucces, Toast.LENGTH_SHORT ).show()
    loginViewModel.vaciarMsg()
    return !statusPantalla
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

/****
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(estadoDrawer)
}
****/
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    // BottomNavigationBar()
}