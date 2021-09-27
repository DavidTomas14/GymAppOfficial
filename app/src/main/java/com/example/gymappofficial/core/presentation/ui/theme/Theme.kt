package com.example.gymappofficial.core.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CursorBotones,
    background = DeepBlue,
    onBackground = TextWhite,
    onPrimary = TextWhite,
    surface = Gray,
    onSurface = TextWhite
)


@Composable
fun GymAppOfficialTheme(
    content: @Composable() () -> Unit
){
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}