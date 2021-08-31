package com.example.gymappofficial.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.example.gymappofficial.presentation.ui.theme.DeepBlue
import com.example.gymappofficial.presentation.ui.theme.GymAppOfficialTheme
import com.example.gymappofficial.presentation.ui.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppOfficialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = DeepBlue) {
                  Navigation()
                }
            }
        }
    }
}
