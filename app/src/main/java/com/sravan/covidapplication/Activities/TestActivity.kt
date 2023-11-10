package com.sravan.covidapplication.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sravan.covidapplication.R

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            FirstTest()
        }
    }
}

@Composable
fun FirstTest() {
    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top ) {

            MainLayout(color = Color.White, name = "Sravan", textColor = Color.Black)
            MainLayout(color = Color.Red, name = "Android", textColor = Color.White)
            MainLayout(Color.Green, "Developer", Color.Black)

            Row(modifier = Modifier.fillMaxWidth()) {
                MainLayout(Color.Blue, "World", Color.White)
                MainLayout(Color.DarkGray, "Developer", Color.White)
            }

            MainLayout(Color.Blue, "World", Color.White)
            MainLayout(Color.DarkGray, "Developer", Color.White)
        }
    }
}

@Composable
fun MainLayout(color: Color, name: String, textColor: Color) {

    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(10.dp),
        color = color
        ) {
        Greeting(name = name, color = textColor)
    }
}

@Composable
fun Greeting(name: String, color: Color) {
    Text(
        text = name,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(25.dp, 10.dp, 5.dp, 7.dp),
        fontSize = 16.0.sp,
        color = color

        )
}

@Preview(fontScale = 1.5f)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstTest()
}