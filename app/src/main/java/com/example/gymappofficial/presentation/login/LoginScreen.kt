package com.example.gymappofficial.presentation.login

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.components.SpannableClickableText
import com.example.gymappofficial.presentation.components.StandardTextField
import com.example.gymappofficial.presentation.ui.theme.*
import com.example.gymappofficial.presentation.ui.util.Screen

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
                //.padding(horizontal = PaddingLarge)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.iniciar_sesion),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                text = loginviewModel.usuario.value,
                hint = stringResource(id = R.string.usuario),
                onValueChange = {
                    loginviewModel.setUsuario(it)
                },
                color = Gray,
                error = loginviewModel.usuarioError.value
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = loginviewModel.contrasena.value,
                hint = stringResource(id = R.string.contrasena),
                onValueChange = {
                    loginviewModel.setContrasena(it)
                },
                onToggleClickChange = {
                    loginviewModel.setPasswordVisibility(it)
                },
                isPasswordVisible = loginviewModel.isPasswordVisible.value,
                keyboardType = KeyboardType.Password,
                color = Gray,
                error = loginviewModel.contrasenaError.value
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.GruposMuscularesScreen.route)
                },
                shape = RoundedCornerShape(50f),
                modifier = Modifier
                    .align(Alignment.End),
            ) {
                Text(
                    text = stringResource(id = R.string.iniciar_sesion),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textAlign = TextAlign.Center,

                )
            }
        }
        SpannableClickableText(
            navController = navController,
            textNoClickable = stringResource(id = R.string.no_tienes_cuenta),
            textClickable = stringResource(id = R.string.registrate),
            tag = stringResource(id = R.string.registrarse),
            route = Screen.RegisterScreen.route,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

