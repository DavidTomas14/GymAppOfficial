package com.example.gymappofficial.feature_auth.domain.use_case

import com.example.gymappofficial.core.util.ValidationUtil
import com.example.gymappofficial.feature_auth.domain.models.AuthError
import com.example.gymappofficial.feature_auth.domain.models.LoginResult
import com.example.gymappofficial.feature_auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String) : LoginResult{
        val usernameError = if (username.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null
        if (usernameError != null || passwordError!= null){

            return LoginResult(usernameError, passwordError)
        }
        val result = repository.login(username, password)
        return LoginResult(result = result)
    }
}