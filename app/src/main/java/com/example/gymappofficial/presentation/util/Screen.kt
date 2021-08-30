package com.example.gymappofficial.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object GruposMuscularesScreen : Screen("gruposmusculares_screen")
    object EjerciciosScreen : Screen("ejercicios_screen")
    object EjercicioDetailScreen : Screen("ejerciciodetail_screen")
    object AddEjercicioScreen : Screen("addejercicio_screen")
    object InfoEjercicioScreen : Screen("infoejercico_screen")
    object GraficoEjercicioScreen : Screen("graficoejercicio_screen")
}