package com.example.gymappofficial.feature_weights.domain.use_case

import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.feature_weights.domain.repository.WeightRepository

class CreateWeightUseCase(
    private val repository: WeightRepository
) {
    suspend operator fun invoke(exerciseId: String,weight: Float,comment: String): SimpleResource{
        return repository.createWeight(exerciseId, weight, comment)
    }

}