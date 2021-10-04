package com.example.gymappofficial.feature_exercises.data.models

import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.toMuscularGroup

data class ExerciseApiModel(
        val userId: String,
        val name: String,
        val description: String,
        val muscularGroup: String,
        val inscreaseWeightComment: String,
        val increasinWeightValoration: Int,
        val id: String
    )

    fun ExerciseApiModel.toExercise() : Exercise {
        return Exercise(
            userId = userId,
            name = name,
            description = description,
            muscularGroup = muscularGroup.toMuscularGroup()!!,
            inscreaseWeightComment = inscreaseWeightComment,
            increasingWeightValoration = increasinWeightValoration,
            id = id
        )
    }
