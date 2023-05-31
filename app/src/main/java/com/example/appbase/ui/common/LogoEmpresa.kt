package com.example.appbase.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appbase.R

@Composable
fun LogoEmpresa(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_title),
        modifier = Modifier.width(150.dp).height(100.dp)
    )
    // Text(text = stringResource(R.string.app_title), fontSize = 18.sp)
}