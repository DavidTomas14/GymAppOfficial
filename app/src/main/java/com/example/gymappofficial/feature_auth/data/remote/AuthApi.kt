package com.example.gymappofficial.feature_auth.data.remote

import com.example.gymappofficial.core.data.dto.response.BasicApiResponse
import com.example.gymappofficial.feature_auth.data.dto.request.AccountAuthRequest
import com.example.gymappofficial.feature_auth.data.dto.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun register(
        @Body request: AccountAuthRequest
    ): BasicApiResponse<Unit>

    @POST("/api/user/login")
    suspend fun login(
        @Body request: AccountAuthRequest
    ): BasicApiResponse<AuthResponse>

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object{
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}