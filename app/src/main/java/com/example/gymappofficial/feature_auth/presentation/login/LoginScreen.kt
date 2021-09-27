package com.example.gymappofficial.feature_auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.components.SpannableClickableText
import com.example.gymappofficial.presentation.components.StandardTextField
import com.example.gymappofficial.core.presentation.ui.theme.*
import com.example.gymappofficial.core.presentation.util.UiEvent
import com.example.gymappofficial.core.presentation.util.asString
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.feature_auth.domain.models.AuthError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    loginviewModel: LoginViewModel = hiltViewModel()
) {
    val username = loginviewModel.username.value
    val password = loginviewModel.password.value
    val loginState = loginviewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        loginviewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvent.Navigate -> {
                    navController.popBackStack()
                    navController.navigate(event.route)
                }
            }
        }
    }


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
                text = username.text,
                hint = stringResource(id = R.string.usuario),
                onValueChange = {
                    loginviewModel.onEvent(LoginEvent.EnteredUsername(it))
                },
                color = Gray,
                error = when (username.error) {
                    is AuthError.FieldEmpty -> {
                        stringResource(id = R.string.this_field_cant_be_empty)
                    }
                    else -> ""
                }
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = password.text,
                hint = stringResource(id = R.string.contrasena),
                onValueChange = {
                    loginviewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                onToggleClickChange = {
                    loginviewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                },
                isPasswordVisible = loginState.isPasswordVisible,
                keyboardType = KeyboardType.Password,
                color = Gray,
                error = when (password.error) {
                    is AuthError.FieldEmpty -> {
                        stringResource(id = R.string.this_field_cant_be_empty)
                    }
                    else -> ""
                }
            )

            Spacer(modifier = Modifier.height(PaddingMedium))
            Button(
                onClick = {
                    loginviewModel.onEvent(LoginEvent.Login)
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
            if (loginState.isLoading) {
                CircularProgressIndicator()
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

