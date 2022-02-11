package com.example.gymappofficial.core.util

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen(val route: String) {
    @SuppressLint("CustomSplashScreen")
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MuscularGroupScreen : Screen("muscular_group")
    object MuscularGroupExercisesScreen : Screen("exercises_screen")
    object AddExerciseScreen : Screen("addexercise_screen")
    object ExerciseInfoScreen : Screen("exerciseinfo_screen")
    object ExerciseHistoryScreen : Screen("exercisehistory_screen")
    object AddWeightScreen: Screen("addweight_screen")
}
