package com.example.appbase.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appbase.domain.repository.SeguimientoRepository
import com.example.appbase.ui.fragment.screen.*
import com.example.appbase.ui.fragment.screen.LoginScreen
import com.example.appbase.ui.viewmodel.LoginViewModel
import com.example.appbase.ui.viewmodel.SeguimientoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Navigation(navController: NavHostController, loginViewModel: LoginViewModel) {
    //val seguimientoViewModel= SeguimientoViewModel()
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
            HomeScreen(navController, loginViewModel)
        }
        composable(NavigationItem.Seguimiento.route) {
           // Seguimiento(seguimientoViewModel)
            Seguimiento()
        }
        composable(NavigationItem.Pago.route) {
            Pagoonline()
        }
        composable(NavigationItem.Cotizar.route) {
            Cotizacion()
        }
        composable(NavigationItem.MyCuentaScreen.route) {
            MyCuenta()
        }
        composable(NavigationItem.AppAboutScreen.route) {
            AppAbout()
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