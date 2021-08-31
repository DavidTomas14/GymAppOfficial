package com.example.gymappofficial.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappofficial.R


val quickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicsand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = TextWhite
    ),
    h1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        color = TextWhite
    ),
    h2 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        color = TextWhite
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = TextWhite
    )

)