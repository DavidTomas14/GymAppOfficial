package com.example.gymappofficial.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymappofficial.core.presentation.components.StandardScaffold
import com.example.gymappofficial.core.presentation.ui.theme.GymAppOfficialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
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
                    state = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigation(navController = navController, scaffoldState = scaffoldState)
                }
            }
        }

    }
}



