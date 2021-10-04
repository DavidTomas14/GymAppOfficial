package com.example.gymappofficial.feature_exercises.domain.repository

import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.MuscularGroupType
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_exercises.data.models.ExerciseApiModel

interface ExercisesRepository {

    suspend fun createExercise(
        name: String,
        description: String,
        muscularGroup: String
    ): SimpleResource

    suspend fun getExercisesByMuscularGroup(
        muscularGroup: String
    ): Resource<List<Exercise>>

    suspend fun getExerciseById(
        exerciseId: String
    ): Resource<Exercise>

    suspend fun updateExercise(
        exerciseId: String,
        description: String,
        increaseWeightComment: String,
        increasingWeightValoration: Int
    ): SimpleResource

    suspend fun deleteExerciseById(
        exerciseId: String,
    ): SimpleResource

    suspend fun getLastWeightFromExercise(
        exerciseId: String
    ): Resource<Float>


    suspend fun getMaxWeightFromExercise(
        exerciseId: String
    ): Resource<Float>
}