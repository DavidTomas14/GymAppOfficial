package com.example.gymappofficial.feature_exercises.data.dto.request



data class UpdateExerciseRequest(
    val exerciseId: String,
    val description: String,
    val increaseWeightComment: String,
    val increasingWeightValoration: Int,
)
