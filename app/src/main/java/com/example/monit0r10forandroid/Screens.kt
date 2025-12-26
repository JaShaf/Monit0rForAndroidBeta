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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screens(val route: String) {
    object Title : Screens("title")
    object Settings : Screens("settings")
    object EmployerOrgDetails : Screens("details")
    object EmployeesPlayers : Screens("members")
}

@Composable
fun TitleScreens(navController: NavController) {
    Column {
        Text("Monit0r")
        Button(onClick = {
            navController.navigate(Screens.EmployerOrgDetails.route)
        }) {
            Text("Welcome")
        }
        Button(onClick = {
            navController.navigate(Screens.Settings.route)
        }) {
            Text("Settings")
        }

    }
}

@Composable
fun SettingsScreens(navController: NavController) {
    Column {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}

@Composable
fun EmployerOrgDetailsScreens(navController: NavController) {
    Column {
        Button(onClick = {
            navController.navigate(Screens.EmployeesPlayers.route)
        }) {
            Text("Team Members")
        }
        Button(onClick = {
            navController.navigate(Screens.Settings.route)
        }) {
            Text("Settings")
        }
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}

@Composable
fun EmployeesPlayersScreens(navController: NavController) {
    Column {
        Button(onClick = {
            navController.navigate(Screens.EmployerOrgDetails.route)
        }) {
            Text("Organization Details")
        }
        Button(onClick = {
            navController.navigate(Screens.Settings.route)
        }) {
            Text("Settings")
        }
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}