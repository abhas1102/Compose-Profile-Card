package com.example.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profilecardlayout.ui.theme.ProfileCardLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {

                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray) {
            ProfileCard()

        }
    }

    @Composable
    fun ProfileCard(){
        Card(modifier = Modifier.padding(16.dp).fillMaxWidth().wrapContentHeight(align = Alignment.Top),elevation = 8.dp) {

            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
                ProfilePicture()
                ProfileContent()
            }

        }
    }
    @Composable
    fun ProfilePicture(){
        Card(shape = CircleShape, border = BorderStroke(width = 2.dp, color = Color.Green),
        modifier = Modifier.padding(16.dp),elevation = 4.dp) {
            Image(painter = painterResource(id = R.drawable.neweasyday),
                contentDescription ="Content description" ,
                modifier = Modifier.size(72.dp),contentScale = ContentScale.Crop)

        }


    }
    @Composable
    fun ProfileContent(){
        Text(text = "John Doe")

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}