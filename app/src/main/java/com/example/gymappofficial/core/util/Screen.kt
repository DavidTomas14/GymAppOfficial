package com.example.gymappofficial.core.util

import android.annotation.SuppressLint

sealed class Screen(val route: String) {
    @SuppressLint("CustomSplashScreen")
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MuscularGroupScreen : Screen("gruposmusculares_screen")
    object MuscularGroupExercisesScreen : Screen("ejercicios_screen")
    object AddExerciseScreen : Screen("addejercicio_screen")
    object ExerciseInfoScreen : Screen("infoejercico_screen")
    object ExerciseHistoryScreen : Screen("graficoejercicio_screen")
    object AddPesoScreen: Screen("addpeso_screen")
}