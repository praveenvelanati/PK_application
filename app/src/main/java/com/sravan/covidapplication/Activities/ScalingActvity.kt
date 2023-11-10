package com.sravan.covidapplication.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sravan.covidapplication.Activities.ui.theme.CovidApplicationTheme
import com.sravan.covidapplication.R

class ScalingActvity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CovidApplicationTheme() {
                SetUpUI()
            }
        }
    }
}

@Composable
fun SetUpUI() {

    var isFontTooBig by remember { mutableStateOf(false) }

    Surface(color = colorResource(id = R.color.main), modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()) {

            MessageCard(name = "How are you?")
            MessageCard(name = "I am good")

        }
    }
}

@Composable
fun MessageCard(name: String) {

 //   MaterialTheme(typography = Typo) {

        Text(
            text = "Hello Sravan, $name", modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(5.dp, 10.dp, 5.dp, 10.dp), style = MaterialTheme.typography.body1
        )
  //  }


}

@Preview(fontScale = 1f)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SetUpUI()
}