package com.example.gymappofficial.core.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object GruposMuscularesScreen : Screen("gruposmusculares_screen")
    object EjerciciosGrupoMuscularScreen : Screen("ejercicios_screen")
    object AddEjercicioScreen : Screen("addejercicio_screen")
    object InfoEjercicioScreen : Screen("infoejercico_screen")
    object HistorialEjercicioScreen : Screen("graficoejercicio_screen")
    object AddPesoScreen: Screen("addpeso_screen")
}