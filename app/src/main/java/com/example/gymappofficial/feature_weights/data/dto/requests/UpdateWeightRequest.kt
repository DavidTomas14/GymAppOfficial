package com.example.gymappofficial.feature_weights.data.dto.requests

data class UpdateWeightRequest(
    val weightId: String,
    val weight: Float,
    val comment: String,
)
