package com.example.gymappofficial.feature_auth.presentation.register

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
import com.example.gymappofficial.core.presentation.util.asString
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.feature_auth.domain.models.AuthError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val username = registerViewModel.usernameState.value
    val password = registerViewModel.passwordState.value
    val repeatedPassword = registerViewModel.repeatedPassword.value
    val registerState = registerViewModel.registerState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        registerViewModel.eventFlow.collectLatest {event->
            when(event) {
                is RegisterViewModel.UiEvent.SnackBarEvent ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Short
                    )
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
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = stringResource(id = R.string.registro),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                color = Gray,
                text = username.text,
                hint = stringResource(id = R.string.usuario),
                error = when (username.error) {
                   AuthError.FieldEmpty -> {
                        stringResource(id = R.string.this_field_cant_be_empty)
                    }
                    AuthError.InputTooShort -> {
                        stringResource(id = R.string.the_username_is_too_short)
                    }
                    else -> ""
                },
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.EnteredUsername(it))
                })

            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                color = Gray,
                keyboardType = KeyboardType.Password,
                text = password.text,
                hint = stringResource(
                    id = R.string.contrasena
                ),
                error = when (password.error) {
                    AuthError.FieldEmpty -> {
                        stringResource(id = R.string.this_field_cant_be_empty)
                    }
                    AuthError.InputTooShort -> {
                        stringResource(id = R.string.the_password_is_too_short)
                    }
                    AuthError.InvalidPassword -> {
                        stringResource(id = R.string.invalid_password_entered)
                    }
                    AuthError.PasswordsDontMatch -> {
                        stringResource(id = R.string.passwords_dont_match)
                    }
                    else -> ""
                },
                isPasswordVisible = password.isVisible,
                onToggleClickChange = {
                    registerViewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.EnteredPassword(it))
                }
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            StandardTextField(
                color = Gray,
                keyboardType = KeyboardType.Password,
                text = repeatedPassword.text,
                hint = stringResource(
                    id = R.string.confirma_contrasena
                ),
                isPasswordVisible = password.isVisible,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.EnteredRepeatedPassword(it))
                }
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            Button(
                enabled = !registerState.isLoading,
                onClick = {registerViewModel.onEvent(RegisterEvent.Register)},
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
            if (registerState.isLoading){
                CircularProgressIndicator()
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