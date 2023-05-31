package com.example.appbase.ui.nav

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.appbase.R
import com.example.appbase.ui.common.ConnectivityStatus
import com.example.appbase.ui.common.LogoEmpresa
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            LogoEmpresa()
            ConnectivityStatus()
        },
        backgroundColor = colorResource(id = R.color.colorPrimaryPi),
        contentColor = Color.White
    )
}