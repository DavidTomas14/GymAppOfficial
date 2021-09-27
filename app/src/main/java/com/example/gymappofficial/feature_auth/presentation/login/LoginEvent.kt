package com.example.gymappofficial.feature_auth.presentation.login

sealed class LoginEvent(){
    data class EnteredUsername(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    object TogglePasswordVisibility: LoginEvent()
    object Login: LoginEvent()
}
