package com.example.gymappofficial.di

import com.example.gymappofficial.feature_auth.data.remote.AuthApi
import com.example.gymappofficial.feature_auth.data.repository.AuthRepositoryImpl
import com.example.gymappofficial.feature_auth.domain.repository.AuthRepository
import com.example.gymappofficial.feature_auth.domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient) : AuthApi {
        return Retrofit.Builder()
            .baseUrl(AuthApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun registerUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
}