package com.example.gymappofficial.di

import com.example.gymappofficial.feature_exercises.data.remote.ExercisesApi
import com.example.gymappofficial.feature_exercises.data.remote.ExercisesApi.Companion.BASE_URL
import com.example.gymappofficial.feature_exercises.data.repository.ExerciseRepositoryImpl
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository
import com.example.gymappofficial.feature_exercises.domain.use_case.DeleteExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExercisesByMuscularGroupUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.UpdateExerciseUseCase
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
object ExercisesModule {

    @Provides
    @Singleton
    fun provideExerciseApi(client: OkHttpClient): ExercisesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExercisesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExercisesRespository(api: ExercisesApi): ExercisesRepository{
        return ExerciseRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providegetExercisesUseCase(repository: ExercisesRepository): GetExercisesByMuscularGroupUseCase{
        return GetExercisesByMuscularGroupUseCase(repository)
    }

    @Provides
    @Singleton
    fun providegetExerciseByIdUseCase(repository: ExercisesRepository): GetExerciseByIdUseCase{
        return GetExerciseByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateExerciseUsecase(repository: ExercisesRepository): UpdateExerciseUseCase{
        return UpdateExerciseUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteExerciseByIdUseCase(repository: ExercisesRepository): DeleteExerciseByIdUseCase{
        return DeleteExerciseByIdUseCase(repository)
    }

}