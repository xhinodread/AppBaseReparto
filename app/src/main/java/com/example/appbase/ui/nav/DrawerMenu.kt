package com.example.appbase.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    estadoDrawer : ScaffoldState
){
    Box(){
        Column() {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        navController.navigate(NavigationItem.MyCuentaScreen.route)
                        coroutineScope.launch { estadoDrawer.drawerState.close() }
                    }) {
                Image(imageVector = Icons.Filled.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "My Cuenta", fontSize = 30.sp)
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        navController.navigate(NavigationItem.AppAboutScreen.route)
                        coroutineScope.launch { estadoDrawer.drawerState.close() }
                    }) {
                Image(imageVector = Icons.Filled.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "About App", fontSize = 30.sp)
            }
        }
    }
}