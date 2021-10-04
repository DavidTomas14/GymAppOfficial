package com.example.gymappofficial.feature_exercises.presentation.add_exercise

sealed class AddExerciseEvent{
    data class EnteredName(val value: String): AddExerciseEvent()
    data class EnteredDescription(val value: String): AddExerciseEvent()
    data class Create(val musculargroup: String): AddExerciseEvent()
}
