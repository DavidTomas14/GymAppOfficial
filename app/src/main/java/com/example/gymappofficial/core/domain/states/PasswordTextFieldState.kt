package com.example.gymappofficial.core.domain.states

import com.example.gymappofficial.core.util.Error

data class PasswordTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val isVisible: Boolean = false
) {


}