package com.example.appbase.ui.fragment.screen

import android.graphics.Color.alpha
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appbase.R
import com.example.appbase.ui.nav.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    var startAnimator by remember { mutableStateOf(false) }
    val tiempoEspera:Long = 1500
    val tiempoTransicion:Int = 3000

    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimator) 0f else 1f,
        animationSpec = tween(
            durationMillis = tiempoTransicion
        )
    )

    LaunchedEffect(key1 = true){
        startAnimator = true
        delay(tiempoEspera)
        navController.popBackStack()
        navController.navigate(NavigationItem.Home.route)
        //navController.navigate(NavigationItem.LoginScreen.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            "Bienveni@s",
            Modifier.alpha(alpha=alpha),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.capturahomepiero),
            contentDescription = stringResource(id = R.string.app_title),
            Modifier.size(150.dp, 150.dp).alpha(alpha=alpha)
        )
        /**
         *  Image(
        painter = painterResource(id = R.drawable.logochileregion),
        contentDescription = "Logo Chileregión",
        Modifier.size(150.dp, 150.dp).alpha(alpha=alpha)
        )

         **/

    }
}
