package com.example.gymappofficial.feature_auth.domain.use_case

import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.core.util.ValidationUtil
import com.example.gymappofficial.feature_auth.domain.models.AuthError
import com.example.gymappofficial.feature_auth.domain.models.RegisterResult
import com.example.gymappofficial.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        repeatedPassword: String
    ): RegisterResult {
        val trimmedUsername = username.trim()

        val usernameError = ValidationUtil.validateUsername(trimmedUsername)
        val passwordError = ValidationUtil.validatePassword(password, repeatedPassword)

        if (usernameError != null || passwordError!= null){
            return RegisterResult(usernameError, passwordError)
        }

        val result = repository.register(username, password)
        return RegisterResult(result = result)


    }
}