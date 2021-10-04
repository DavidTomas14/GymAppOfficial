package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class DeleteExerciseByIdUseCase(
    val repository: ExercisesRepository
) {
    suspend operator fun invoke(exerciseId: String) : SimpleResource {
        return repository.deleteExerciseById(exerciseId)
    }
}