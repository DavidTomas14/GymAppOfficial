package com.example.gymappofficial.feature_exercises.data.dto.request

import com.example.gymappofficial.core.domain.models.MuscularGroupType

data class CreateExerciseRequest(
    val name: String,
    val description: String,
    val muscularGroup: String,
)
