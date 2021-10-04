package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.feature_exercises.data.models.ExerciseApiModel
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class GetExerciseByIdUseCase(
    private val repository: ExercisesRepository
) {

    suspend operator fun invoke(exerciseId: String): Resource<Exercise>{
        return repository.getExerciseById(exerciseId)

    }
}