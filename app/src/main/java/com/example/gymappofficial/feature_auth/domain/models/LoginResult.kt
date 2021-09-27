package com.example.gymappofficial.feature_auth.domain.models

import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource

data class LoginResult(
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
