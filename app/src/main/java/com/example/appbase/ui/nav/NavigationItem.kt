package com.example.appbase.ui.nav

import com.example.appbase.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Seguimiento : NavigationItem("seguimiento", R.drawable.ic_profile, "Seguimiento")
    object Pago : NavigationItem("pago", R.drawable.ic_book, "Pagos")
    object Cotizar : NavigationItem("cotizar", R.drawable.ic_music, "Cotizar")
    object Music : NavigationItem("music", R.drawable.ic_music, "Pendientes")
    object Movies : NavigationItem("movies", R.drawable.ic_movie, "Entregados")
    object Books : NavigationItem("books", R.drawable.ic_book, "Books")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "Perfil")
    object SplashScreen : NavigationItem("SplashScreen", R.drawable.ic_profile, "SplashScreen")
    object LoginScreen : NavigationItem("LoginScreen", R.drawable.ic_profile, "LoginScreen")
}

