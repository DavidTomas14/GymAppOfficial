package com.example.gymappofficial.feature_exercises.domain.use_case

import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.MuscularGroupType
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository

class AddExerciseUseCase(
    val repository: ExercisesRepository
) {
    suspend operator fun invoke(name : String, description: String, muscularGroup: String): SimpleResource {
        if (name.isBlank() || description.isBlank()){
            return Resource.Error(uiText = UiText.StringResource(R.string.this_field_cant_be_empty))
        }
        return repository.createExercise(name, description, muscularGroup)
    }
}