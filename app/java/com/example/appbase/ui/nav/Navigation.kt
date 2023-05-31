package com.example.appbase.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appbase.ui.fragment.screen.*
import com.example.appbase.ui.viewmodel.LoginViewModel

@Composable
fun Navigation(navController: NavHostController, loginViewModel: LoginViewModel) {
    NavHost(
        navController,
        startDestination = NavigationItem.SplashScreen.route
        //startDestination = NavigationItem.LoginScreen.route
    ) {

        composable( NavigationItem.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(NavigationItem.LoginScreen.route) {
            LoginScreen({},  loginViewModel)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(loginViewModel)
        }
        composable(NavigationItem.Seguimiento.route) {
            Seguimiento()
        }
        composable(NavigationItem.Pago.route) {
            Pagoonline()
        }
        composable(NavigationItem.Cotizar.route) {
            Cotizacion()
        }
        /***
        composable(NavigationItem.Movies.route) {
            MoviesScreen()
        }
        ****/
        /***
        composable(NavigationItem.Books.route) {
        BooksScreen()
        }
         */
        /***
        composable(NavigationItem.Books.route) {
        BooksScreen()
        }
         */
        /****
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
        ****/
    }
}