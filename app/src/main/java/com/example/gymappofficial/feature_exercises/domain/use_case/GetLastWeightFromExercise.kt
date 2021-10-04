package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class GetLastWeightFromExercise(
    val repository: ExercisesRepository
) {
    suspend operator fun invoke(exerciseId: String) : Resource<Float> {
        return repository.getLastWeightFromExercise(exerciseId)
    }
}