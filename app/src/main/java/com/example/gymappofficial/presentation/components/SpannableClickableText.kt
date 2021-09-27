package com.example.gymappofficial.presentation.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymappofficial.core.presentation.ui.theme.CursorBotones
import com.example.gymappofficial.core.presentation.ui.theme.TextWhite

@Composable
fun SpannableClickableText(
    navController:NavController,
    textNoClickable: String,
    textClickable: String,
    tag: String,
    route: String,
    modifier: Modifier
) {
    val annotatedText = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = TextWhite
            )
        ) {
            append(textNoClickable)
        }
        append(" ")

        pushStringAnnotation(
            tag = tag,
            annotation = tag
        )
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = CursorBotones
            )
        ) {
            append(textClickable)
        }
        pop()
    }
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            )[0].let {
                navController.popBackStack()
                navController.navigate(route)
            }
        },
        modifier = modifier
    )

}
