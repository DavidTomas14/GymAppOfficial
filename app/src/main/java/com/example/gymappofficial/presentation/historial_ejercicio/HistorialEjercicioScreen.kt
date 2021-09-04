package com.example.gymappofficial.presentation.historial_ejercicio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gymappofficial.presentation.components.StandardScaffold
import com.example.gymappofficial.presentation.ui.util.Screen

@Composable
fun HistorialEjercicioScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Historial Ejercicio Screen")
    }
}
