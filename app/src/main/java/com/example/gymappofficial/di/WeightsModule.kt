package com.example.gymappofficial.di

import com.example.gymappofficial.feature_weights.data.remote.WeightsApi
import com.example.gymappofficial.feature_weights.data.repository.WeightRepositoryImpl
import com.example.gymappofficial.feature_weights.domain.repository.WeightRepository
import com.example.gymappofficial.feature_weights.domain.use_case.GetWeightsUseCase
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
object WeightsModule {
    @Provides
    @Singleton
    fun provideWeightsApi(client: OkHttpClient): WeightsApi {
        return Retrofit.Builder()
            .baseUrl(WeightsApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeightsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeightsRepository(api: WeightsApi): WeightRepository {
        return WeightRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetWeightsUseCase(repository: WeightRepository): GetWeightsUseCase{
        return GetWeightsUseCase(repository)
    }

}