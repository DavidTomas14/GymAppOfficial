package com.example.gymappofficial.presentation.ui.util

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.gymappofficial.core.util.Constants.NAVARG_EXERCISE_ID
import com.example.gymappofficial.core.util.Constants.NAVARG_MUSCULAR_GROUP
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.feature_exercises.presentation.add_exercise.AddEjercicioScreen
import com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group.ExercisesMuscularGroupScreen
import com.example.gymappofficial.feature_home_screen.presentation.GruposMuscularesScreen
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.HistorialEjercicioScreen
import com.example.gymappofficial.feature_exercises.presentation.exercise_info.InfoEjercicioScreen
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
            route = Screen.MuscularGroupExercisesScreen.route + "/{$NAVARG_MUSCULAR_GROUP}",
            arguments = listOf(
                navArgument(NAVARG_MUSCULAR_GROUP) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            ),
        ) { backStackEntry ->
            ExercisesMuscularGroupScreen(navController = navController, scaffoldState= scaffoldState, muscularGroup = backStackEntry.arguments?.getString(
                NAVARG_MUSCULAR_GROUP))
        }

        composable(Screen.AddExerciseScreen.route) {
            AddEjercicioScreen(navController = navController)
        }

        composable(
            route = Screen.ExerciseInfoScreen.route + "/{${NAVARG_EXERCISE_ID}}",
            arguments = listOf(
                navArgument(NAVARG_EXERCISE_ID){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { backstackEntry ->
            InfoEjercicioScreen(navController = navController,scaffoldState= scaffoldState)
        }

        composable(Screen.ExerciseHistoryScreen.route) {
            HistorialEjercicioScreen(navController = navController, scaffoldState = scaffoldState)
        }

    }
}