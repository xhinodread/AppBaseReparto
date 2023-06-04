package com.example.appbase.ui.nav

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.appbase.R
import com.example.appbase.ui.common.ConnectivityStatus
import com.example.appbase.ui.common.LogoEmpresa
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopBar(
    estadoDrawer : ScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(
        title = {
            LogoEmpresa()
            ConnectivityStatus()
        },
        backgroundColor = colorResource(id = R.color.colorPrimaryPi),
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    estadoDrawer.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        }
    )
}