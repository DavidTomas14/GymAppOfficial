package com.example.gymappofficial.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.ui.util.Screen
import com.example.gymappofficial.presentation.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(5f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
        delay(Constants.SPLASH_SCREEN_DURATION)
        navController.popBackStack()
        navController.navigate(Screen.LoginScreen.route)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.foto_cv),
            contentDescription = "Foto CV",
            modifier = Modifier.scale(scale.value)
        )
    }
}
