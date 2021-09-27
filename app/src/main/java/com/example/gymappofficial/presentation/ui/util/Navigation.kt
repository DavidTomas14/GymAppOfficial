package com.example.gymappofficial.presentation.ui.util

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.presentation.addejercicio.AddEjercicioScreen
import com.example.gymappofficial.presentation.ejercicios_grupomuscular.EjerciciosGrupoMuscularScreen
import com.example.gymappofficial.presentation.gruposmusculares.GruposMuscularesScreen
import com.example.gymappofficial.presentation.historial_ejercicio.HistorialEjercicioScreen
import com.example.gymappofficial.presentation.info_ejercicio.InfoEjercicioScreen
import com.example.gymappofficial.feature_auth.presentation.login.LoginScreen
import com.example.gymappofficial.feature_auth.presentation.register.RegisterScreen
import com.example.gymappofficial.feature_auth.presentation.splash.SplashScreen
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MuscularGroupScreen.route) {
            GruposMuscularesScreen(navController = navController)
        }
        composable(
            route = Screen.MuscularGroupExercisesScreen.route + "/{nombre_grupo_muscular}",
            arguments = listOf(
                navArgument("nombre_grupo_muscular") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            ),
        ) { backStackEntry ->
            EjerciciosGrupoMuscularScreen(navController = navController, scaffoldState= scaffoldState, grupoMuscular = backStackEntry.arguments?.getString("nombre_grupo_muscular"))
        }

        composable(Screen.AddExerciseScreen.route) {
            AddEjercicioScreen(navController = navController)
        }

        composable(
            route = Screen.ExerciseInfoScreen.route + "/{id_ejercicio}",
            arguments = listOf(
                navArgument("id_ejercicio"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { backstackEntry ->
            InfoEjercicioScreen(navController = navController,scaffoldState= scaffoldState, idEjercicio = backstackEntry.arguments?.getString("id_ejercicio"))
        }

        composable(Screen.ExerciseHistoryScreen.route) {
            HistorialEjercicioScreen(navController = navController)
        }

    }
}