package com.example.monit0r10forandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.monit0r10forandroid.ui.theme.Monit0r10ForAndroidTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monit0r10forandroid.ui.theme.Charcoal
import com.example.monit0r10forandroid.ui.theme.MintGreen
import com.example.monit0r10forandroid.ui.theme.OrgDetailsArray
import com.example.monit0r10forandroid.ui.theme.OrganizationField
import com.example.monit0r10forandroid.ui.theme.RoyalBlue
import com.example.monit0r10forandroid.ui.theme.SoftPink
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

sealed class Screens(val route: String) {
    object Title : Screens("title")
    object Settings : Screens("settings")
    object EmployerOrgDetails : Screens("details")
    object EmployeesPlayers : Screens("members")
}

@Composable
fun TitleScreens(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.monit0r_icon_background),
             null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.15f

        )
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
            painter = painterResource(id = R.drawable.play_store_512),
                null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        }

        Button(onClick = {
            navController.navigate(Screens.EmployerOrgDetails.route)
        },
            shape = RoundedCornerShape(0),
            modifier = Modifier
                .padding(horizontal = 150.dp, vertical = 400.dp)
                .size(150.dp),

            ) {
            Text("Welcome")
        }
//        Button(onClick = {
//            navController.navigate(Screens.Settings.route)
//        },
//            modifier = Modifier
//                .padding(horizontal = 150.dp, vertical = 400.dp)
//                .size(110.dp)
//            ) {
//            Text("Settings")
//        }

    }


//@Composable
//fun SettingsScreens(navController: NavController) {
//    Box (
//        modifier = Modifier
//            .fillMaxSize()
//
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.monit0r_icon_background),
//            null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize(),
//            alpha = 0.15f
//
//        )
//
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 30.dp),
//        ) {
//            Button(
//                onClick = {
//                    navController.popBackStack()
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Charcoal
//                )
//
//            ) {
//                Text("Exit Settings")
//            }
//        }
//    }
//}




@Composable
fun EmployerOrgDetailsScreens(navController: NavController,
                              userPrefs: UserPreferences) {
    Box(
        modifier = Modifier.fillMaxSize()

    )
    {

        Image(
            painter = painterResource(id = R.drawable.monit0r_icon_background),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.15f

        )

        OrgDetailsArray(userPrefs)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                navController.navigate(Screens.EmployeesPlayers.route)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftPink
                )

            ) {
                Text("Team Members")
            }
//            Button(onClick = {
//                navController.navigate(Screens.Settings.route)
//            },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Charcoal
//                )
//
//            ) {
//                Text("Settings")
//            }
            Button(onClick = {
                navController.popBackStack(
                    route = Screens.Title.route,
                    inclusive = false
                )
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MintGreen
                )

            ) {
                Text("Home")
            }
        }
    }
}

@Composable
fun EmployeesPlayersScreens(navController: NavController,
                            userPrefs: UserPreferences) {

    val members by userPrefs.members.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.monit0r_icon_background),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.15f

        )
//        Text(
//            text = "Members from DataStore: ${members.size}",
//            color = Color.Red
//        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MemberCreator { newMember ->
                scope.launch {
                    userPrefs.saveMembers(members + newMember)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            MembersList(
                members = members,
                onRemove = { member ->
                    scope.launch {
                        userPrefs.saveMembers(members - member)
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.navigate(Screens.EmployerOrgDetails.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftPink
                )

            ) {
                Text("Organization Details")
            }
//            Button(
//                onClick = {
//                    navController.navigate(Screens.Settings.route)
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Charcoal
//                )
//
//            ) {
//                Text("Settings")
//            }
            Button(onClick = {
                navController.popBackStack(
                    route = Screens.Title.route,
                    inclusive = false
                )
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MintGreen
                )

            ) {
                Text("Home")
            }
        }
    }
}