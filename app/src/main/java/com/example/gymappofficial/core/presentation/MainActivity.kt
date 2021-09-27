package com.example.gymappofficial.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymappofficial.presentation.components.StandardScaffold
import com.example.gymappofficial.core.presentation.ui.theme.GymAppOfficialTheme
import com.example.gymappofficial.presentation.ui.util.Navigation
import com.example.gymappofficial.core.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppOfficialTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val scaffoldState = rememberScaffoldState()
                // A surface container using the 'background' color from the theme
                StandardScaffold(
                    navController = navController,
                    showBottonBar = navBackStackEntry?.destination?.route in listOf(
                        Screen.ExerciseInfoScreen.route,
                        Screen.ExerciseHistoryScreen.route,
                    ),
                    iconFAB = when (navBackStackEntry?.destination?.route) {
                        Screen.ExerciseInfoScreen.route -> Icons.Default.Edit
                        Screen.ExerciseHistoryScreen.route -> Icons.Default.Add
                        else -> null
                    },
                    state = scaffoldState,
                    showFAB = navBackStackEntry?.destination?.route in listOf(
                        Screen.ExerciseInfoScreen.route,
                        Screen.ExerciseHistoryScreen.route
                    ),
                    modifier = Modifier.fillMaxSize(),
                    onFabClick = {
                        when (navBackStackEntry?.destination?.route) {
                            Screen.ExerciseInfoScreen.route -> {

                            }
                            Screen.ExerciseHistoryScreen.route -> {

                            }
                            else -> null
                        }
                    }
                ) {
                    Navigation(navController = navController, scaffoldState = scaffoldState)
                }
            }
        }

    }
}



