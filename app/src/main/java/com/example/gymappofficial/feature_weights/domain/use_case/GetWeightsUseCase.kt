package com.example.gymappofficial.feature_weights.domain.use_case

import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.feature_weights.domain.repository.WeightRepository

class GetWeightsUseCase(
    private val repository: WeightRepository
) {
    suspend operator fun invoke(exerciseId: String): Resource<List<Weight>> {
        return repository.getWeights(exerciseId)
    }
}