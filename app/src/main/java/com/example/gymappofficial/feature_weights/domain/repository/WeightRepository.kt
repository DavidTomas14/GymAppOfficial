package com.example.gymappofficial.feature_weights.domain.repository

import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource

interface WeightRepository {

    suspend fun createWeight(exerciseId: String, weight: Float, comment: String): SimpleResource

    suspend fun getWeights(exerciseId: String): Resource<List<Weight>>

    suspend fun updateWeight(weightId: String, weight: Float, comment: String): SimpleResource

    suspend fun deleteWeight(weightId: String): SimpleResource
}