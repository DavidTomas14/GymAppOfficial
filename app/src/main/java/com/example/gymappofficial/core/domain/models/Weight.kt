package com.example.gymappofficial.core.domain.models


data class Weight(
    val exerciseId: String,
    val userId: String,
    val weight: Float,
    val comment: String,
    val date: Long,
    val id: String,
)
