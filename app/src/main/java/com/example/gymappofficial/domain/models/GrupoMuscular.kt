package com.example.gymappofficial.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class GrupoMuscular(
    val nombre: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
