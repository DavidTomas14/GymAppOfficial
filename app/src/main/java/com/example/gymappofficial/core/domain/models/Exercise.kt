package com.example.gymappofficial.core.domain.models


data class Exercise(
    val userId: String,
    val name: String,
    val description: String,
    val muscularGroup: MuscularGroupType,
    val inscreaseWeightComment: String,
    val increasingWeightValoration: Int,
    val id: String,
)
