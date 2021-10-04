package com.example.gymappofficial.feature_weights.data.dto.requests


data class CreateWeightRequest(
    val exerciseId: String,
    val weight: Float,
    val comment: String
)
