package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.feature_exercises.data.models.ExerciseApiModel
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class GetExercisesByMuscularGroupUseCase(
    private val repository: ExercisesRepository
) {
    suspend operator fun invoke(muscularGroupType: String): Resource<List<Exercise>>{
        return repository.getExercisesByMuscularGroup(muscularGroupType)
    }
}