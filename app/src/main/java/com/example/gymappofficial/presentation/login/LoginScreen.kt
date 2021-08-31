package com.example.gymappofficial.presentation.login

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.components.StandardTextField
import com.example.gymappofficial.presentation.ui.theme.*

@Composable
fun LoginScreen(
    navController: NavController,
    loginviewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingSmall)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PaddingLarge)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.iniciar_sesion),
                color = TextWhite,
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                text = loginviewModel.username.value,
                hint = stringResource(id = R.string.usuario),
                onValueChange = {
                    loginviewModel.setUsuario(it)
                },
                color = Gray
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = loginviewModel.contrasena.value,
                hint = stringResource(id = R.string.contrasena),
                onValueChange = {
                    loginviewModel.setContrasena(it)
                },
                keyboardType = KeyboardType.Password,
                color = Gray
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10f),
                modifier = Modifier.background(BlueViolet2),
            ) {
                Text(
                    text = stringResource(id = R.string.iniciar_sesion),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                val yaTienesCuenta = stringResource(id = R.string.ya_tienes_cuenta)
                val iniciaSesion = stringResource(id = R.string.inicia_sesion)
                withStyle(
                    style = SpanStyle(
                        color = TextWhite
                    )
                ) {
                    append(yaTienesCuenta)
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = CursorBotones
                    )
                ) {
                    append(iniciaSesion)
                }
            } ,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)

        )

    }

}

