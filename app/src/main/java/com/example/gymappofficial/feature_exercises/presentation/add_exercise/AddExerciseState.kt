package com.example.gymappofficial.feature_exercises.presentation.add_exercise

data class AddExerciseState(
    var name: String = "",
    var description: String = "",
    var isLoading: Boolean = false
)
