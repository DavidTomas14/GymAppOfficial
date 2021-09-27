package com.example.gymappofficial.feature_auth.domain.repository

import com.example.gymappofficial.core.data.dto.response.BasicApiResponse
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_auth.data.dto.request.AccountAuthRequest

interface AuthRepository {

    suspend fun register(
        username: String,
        password: String
    ): SimpleResource


    suspend fun login(
        username: String,
        password: String
    ): SimpleResource
}