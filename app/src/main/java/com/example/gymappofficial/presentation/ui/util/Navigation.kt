package com.example.gymappofficial.presentation.ui.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.gymappofficial.presentation.addejercicio.AddEjercicioScreen
import com.example.gymappofficial.presentation.ejercicios_grupomuscular.EjerciciosGrupoMuscularScreen
import com.example.gymappofficial.presentation.gruposmusculares.GruposMuscularesScreen
import com.example.gymappofficial.presentation.historial_ejercicio.HistorialEjercicioScreen
import com.example.gymappofficial.presentation.info_ejercicio.InfoEjercicioScreen
import com.example.gymappofficial.presentation.login.LoginScreen
import com.example.gymappofficial.presentation.register.RegisterScreen
import com.example.gymappofficial.presentation.splash.SplashScreen
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.GruposMuscularesScreen.route) {
            GruposMuscularesScreen(navController = navController)
        }
        composable(
            route = Screen.EjerciciosGrupoMuscularScreen.route + "/{nombre_grupo_muscular}",
            arguments = listOf(
                navArgument("nombre_grupo_muscular") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            ),
        ) { backStackEntry ->
            EjerciciosGrupoMuscularScreen(navController = navController, grupoMuscular = backStackEntry.arguments?.getString("nombre_grupo_muscular"))
        }

        composable(Screen.AddEjercicioScreen.route) {
            AddEjercicioScreen(navController = navController)
        }

        composable(Screen.InfoEjercicioScreen.route) {
            InfoEjercicioScreen(navController = navController)
        }

        composable(Screen.HistorialEjercicioScreen.route) {
            HistorialEjercicioScreen(navController = navController)
        }

    }
}