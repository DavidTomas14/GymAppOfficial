package com.example.gymappofficial.feature_auth.domain.use_case

import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}