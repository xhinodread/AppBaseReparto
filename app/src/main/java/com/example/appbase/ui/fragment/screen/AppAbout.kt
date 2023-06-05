package com.example.appbase.ui.fragment.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appbase.R
import com.example.appbase.ui.theme.Laranja
import com.example.appbase.ui.theme.Purple200
import com.example.appbase.ui.theme.Purple220

@Composable
fun AppAbout(){
    Column() {

        Box(
            modifier = Modifier
                .background(Purple200)
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text("about")
        }
        Box(
            modifier = Modifier
                .background(Purple220)
                .fillMaxWidth()
                .padding(5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.androidtelefono),
                contentDescription = null
            )
            CutCornerShapeDemo()
        }
        Row() {
            ExampleBox(shape = TriangleShape)
        }
        Column() {
            Row( modifier = Modifier
                .fillMaxWidth()
                .background(Laranja)
            ) {
                ExampleBox2(shape = TriangleShape)

                Image(
                    painter = painterResource(id = R.drawable.kbanner),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Row() {
            ExampleBox(shape = OtraShape)
        }
    }
}

@Composable
fun CutCornerShapeDemo(){
    ExampleBox(shape = CutCornerShape(10.dp))
}

@Composable
fun ExampleBox(shape: Shape){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape)
                .background(Purple200),
            contentAlignment = Alignment.CenterEnd
        ){
            Text("Desarrollo nativo")
        }
    }
}

@Composable
fun ExampleBox2(shape: Shape){
    Column(modifier = Modifier
        .width(150.dp)
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape)
                .background(Purple200),
            contentAlignment = Alignment.CenterEnd
        ){
            Text("Desarrollo nativo")
        }
    }
}

private val TriangleShape = GenericShape { size, _ ->
    // 1)
    moveTo(size.width / 2f, 0f)

    // 2)
    lineTo(size.width, size.height)

    // 3)
    lineTo(0f, size.height)
}

private val OtraShape = GenericShape { size, _ ->
    // 1)
    moveTo(size.width / 2f, 0f)

    // 2)
    lineTo(size.width, size.height- size.height/2)
    lineTo(size.width/2, size.height)

    // 3)
    lineTo(0f, size.height)
}