package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class UpdateExerciseUseCase(
   private val repository: ExercisesRepository
) {

    suspend operator fun invoke(exercise: Exercise): SimpleResource{
        return repository.updateExercise(exercise.id, exercise.description, exercise.inscreaseWeightComment, exercise.increasingWeightValoration)
    }
}