package com.example.gymappofficial.presentation.register

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
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
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
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = stringResource(id = R.string.registro),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                color = Gray,
                text = registerViewModel.usuario.value,
                hint = stringResource(id = R.string.usuario),
                modifier = Modifier ,
                error = registerViewModel.usuarioError.value,
                onValueChange = {
                    registerViewModel.setUsuario(it)
                })

            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                color = Gray,
                keyboardType = KeyboardType.Password,
                text = registerViewModel.constrasena.value,
                hint = stringResource(
                    id = R.string.contrasena
                ),
                error = registerViewModel.contrasenaError.value,
                modifier = Modifier,
                isPasswordVisible = registerViewModel.isPasswordVisible.value,
                onToggleClickChange = {
                    registerViewModel.setPasswordVisibility(it)
                },
                onValueChange = {
                    registerViewModel.setConstrasena(it)
                }
            )
            StandardTextField(
                color = Gray,
                keyboardType = KeyboardType.Password,
                text = registerViewModel.constrasenaRepetida.value,
                hint = stringResource(
                    id = R.string.confirma_contrasena
                ),
                modifier = Modifier,
                isPasswordVisible = registerViewModel.isPasswordVisible.value,
                onValueChange = {
                    registerViewModel.setConstrasenaRepetida(it)
                }
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(50f),
                modifier = Modifier
                    .align(Alignment.End),
            ) {
                Text(
                    text = stringResource(id = R.string.registrarse),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textAlign = TextAlign.Center,

                    )
            }
        }

        SpannableClickableText(
            navController = navController,
            textNoClickable = stringResource(id = R.string.ya_tienes_cuenta),
            textClickable = stringResource(id = R.string.inicia_sesion),
            tag = stringResource(id = R.string.inicia_sesion),
            route = Screen.LoginScreen.route,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}