package com.example.gymappofficial.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymappofficial.presentation.components.StandardScaffold
import com.example.gymappofficial.presentation.ui.theme.DeepBlue
import com.example.gymappofficial.presentation.ui.theme.GymAppOfficialTheme
import com.example.gymappofficial.presentation.ui.util.Navigation
import com.example.gymappofficial.presentation.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppOfficialTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                // A surface container using the 'background' color from the theme
                StandardScaffold(
                    navController = navController,
                    showBottonBar = navBackStackEntry?.destination?.route in listOf(
                        Screen.InfoEjercicioScreen.route,
                        Screen.HistorialEjercicioScreen.route,
                    ),
                    iconFAB = when (navBackStackEntry?.destination?.route) {
                        Screen.InfoEjercicioScreen.route -> Icons.Default.Edit
                        Screen.HistorialEjercicioScreen.route -> Icons.Default.Add
                        else -> null
                    },
                    showFAB = navBackStackEntry?.destination?.route in listOf(
                        Screen.InfoEjercicioScreen.route,
                        Screen.HistorialEjercicioScreen.route
                    ),
                    modifier = Modifier.fillMaxSize(),
                    onFabClick = {
                        when (navBackStackEntry?.destination?.route) {
                            Screen.InfoEjercicioScreen.route -> {

                            }
                            Screen.HistorialEjercicioScreen.route -> {

                            }
                            else -> null
                        }
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }

    }
}



