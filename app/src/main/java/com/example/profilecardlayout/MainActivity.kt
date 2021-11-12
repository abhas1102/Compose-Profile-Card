package com.example.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import com.example.profilecardlayout.ui.theme.lightGreen
import java.security.Provider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {

                UsersApplication()
            }
        }
    }

    @Composable
    fun UsersApplication(userProfiles:List<UserProfile> = userProfileList){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "usersList" ){
            composable("usersList"){
                MainScreen(userProfiles,navController)
            }
            composable("userDetails/{userId}", arguments = listOf(navArgument("userId"){
                type = NavType.IntType
            })){navBackStackEntry ->
                UserProfileDetailScreen(navBackStackEntry.arguments!!.getInt("userId"))
            }
        }
    }

    @Composable
    fun MainScreen(userProfiles:List<UserProfile>,navController: NavHostController?) {

        Scaffold(topBar = {AppBar()}) {
            androidx.compose.material.Surface(modifier = Modifier.fillMaxSize(),
            ) {
               /* Column {
                    for(i in userProfiles){
                        ProfileCard(userProfile = i)
                    }
                } */
                LazyColumn {
                    items(userProfiles) { userProfile->
                        ProfileCard(userProfile = userProfile){
                            navController?.navigate("userDetails/${userProfile.id}")
                        }
                    }
                }

            }
            
        }

    }

    @Composable
    fun AppBar(){
        TopAppBar(navigationIcon = {Icon(Icons.Default.Home,"Content description",modifier = Modifier.padding(horizontal = 12.dp))},
        title = { Text("Messaging Application Users")})
    }


    @Composable
    fun ProfileCard(userProfile: UserProfile,clickAction: () -> Unit){
        Card(modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() }
            ,elevation = 8.dp,backgroundColor = Color.White) {

            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
                ProfilePicture(userProfile.pictureUrl,userProfile.status,72.dp)
                ProfileContent(userProfile.name, userProfile.status,Alignment.Start)
            }

        }
    }
    @Composable
    fun ProfilePicture(pictureUrl:String, onlineStatus:Boolean, imageSize:Dp){
        Card(shape = CircleShape, border = BorderStroke(width = 2.dp, color = if (onlineStatus)
                                                                                MaterialTheme.colors.lightGreen
                                                                                else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),elevation = 4.dp) {

          /*  Image(painter = painterResource(id = drawableId),
                contentDescription ="Content description" ,
                modifier = Modifier.size(72.dp),contentScale = ContentScale.Crop) */
            Image(
                painter = rememberImagePainter(pictureUrl,builder = {transformations(CircleCropTransformation())}),
                contentDescription = "Visually differently abled",
                modifier = Modifier.size(72.dp)
            )

        }


    }
    @Composable
    fun ProfileContent(userName:String, onlineStatus: Boolean, alignment: Alignment.Horizontal){
        Column(modifier = Modifier
            .padding(8.dp),horizontalAlignment = alignment
            ) {
            CompositionLocalProvider(LocalContentAlpha provides (if (onlineStatus)
                                                                            1f else ContentAlpha.medium)){
                Text(text = userName,style = MaterialTheme.typography.h5)

            }


            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium){
                Text(text = if (onlineStatus)
                    "Active Now"
                else "Offline"
                    , style = MaterialTheme.typography.body2)
            }


        }


    }

    @Composable
    fun UserProfileDetailScreen(userId:Int) {
        val userProfile = userProfileList.first { userProfile -> userId==userProfile.id  }

        Scaffold(topBar = {AppBar()}) {
            androidx.compose.material.Surface(modifier = Modifier.fillMaxSize(),
            ) {
                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {
                    ProfilePicture(pictureUrl = userProfile.pictureUrl, onlineStatus =userProfile.status , imageSize = 240.dp)
                    ProfileContent(userName = userProfile.name, onlineStatus = userProfile.status,Alignment.CenterHorizontally)
                }
                    }
        }

        }

    @Preview(showBackground = true)
    @Composable
    fun UserProfileDetailsPreview() {
        ProfileCardLayoutTheme {
            UserProfileDetailScreen(userId = 0)
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ProfileCardLayoutTheme {
            MainScreen(userProfiles = userProfileList,null)
        }

    }
}