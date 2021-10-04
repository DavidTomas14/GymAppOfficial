package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio

import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.core.presentation.components.StandardScaffold

@Composable
fun HistorialEjercicioScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    historialEjercicioViewModel: HistorialEjercicioViewModel = hiltViewModel()
) {
    StandardScaffold(
        navController = navController,
        showToolbar = true,
        showBottonBar = true,
        showNavIcon = false,
        toolbarTitle = "Historial",
        grupoMuscularActual = "",
        iconFAB = Icons.Default.Add,
        showFAB = true,
        onFabClick = {

        },
        state = scaffoldState
    )
    {

    }
}
