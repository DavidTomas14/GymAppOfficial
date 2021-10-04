package com.example.gymappofficial.core.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class MuscularGroupUi(
    val type: MuscularGroupType,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
