package com.example.gymappofficial.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.domain.states.StandardTextFieldState
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_auth.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _username = mutableStateOf(StandardTextFieldState())
    val username: State<StandardTextFieldState> = _username

    private val _password = mutableStateOf(StandardTextFieldState())
    val password: State<StandardTextFieldState> = _password

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredUsername -> {
                _username.value = username.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.TogglePasswordVisibility -> {
                _loginState.value = _loginState.value.copy(
                    isPasswordVisible = !_loginState.value.isPasswordVisible
                )
            }
            is LoginEvent.Login -> {
                login()
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            _username.value = username.value.copy(error = null)
            _password.value = password.value.copy(error = null)
            _loginState.value = LoginState(isLoading = true)

            val loginResult = loginUseCase(
                username = _username.value.text,
                password = _password.value.text
            )
            if (loginResult.usernameError != null) {
                _username.value = username.value.copy(
                    error = loginResult.usernameError
                )
            }
            if (loginResult.passwordError != null) {
                _password.value = password.value.copy(
                    error = loginResult.passwordError
                )
            }
            when (loginResult.result) {
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(loginResult.result.uiText ?: UiText.unknownError())
                    )
                    _loginState.value = LoginState(isLoading = false)
                }
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.MuscularGroupScreen.route)
                    )
                    _loginState.value = LoginState(isLoading = false)
                }
                null -> _loginState.value = LoginState(isLoading = false)
            }
        }
    }



}