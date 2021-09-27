package com.example.gymappofficial.core.util

import com.example.gymappofficial.feature_auth.domain.models.AuthError

object ValidationUtil {

    fun validateUsername(username: String): AuthError? {
        val trimmedUsername = username.trim()

        if (trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
            return AuthError.InputTooShort
        }
        return null
    }

    fun validatePassword(password: String, repeatedPassword: String): AuthError? {
        if (password.isBlank() || repeatedPassword.isBlank()) {
            return AuthError.FieldEmpty
        }
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return AuthError.InputTooShort
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return AuthError.InvalidPassword
        }
        if (password != repeatedPassword) {
            return AuthError.PasswordsDontMatch
        }
        return null
    }
}