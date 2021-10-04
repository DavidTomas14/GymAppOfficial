package com.example.gymappofficial.feature_weights.data.repository

import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.data.dto.request.CreateExerciseRequest
import com.example.gymappofficial.feature_exercises.data.dto.request.UpdateExerciseRequest
import com.example.gymappofficial.feature_exercises.data.models.toExercise
import com.example.gymappofficial.feature_weights.data.dto.requests.CreateWeightRequest
import com.example.gymappofficial.feature_weights.data.dto.requests.UpdateWeightRequest
import com.example.gymappofficial.feature_weights.data.remote.WeightsApi
import com.example.gymappofficial.feature_weights.domain.repository.WeightRepository
import okio.IOException
import retrofit2.HttpException

class WeightRepositoryImpl(
    private val api: WeightsApi
) : WeightRepository {
    override suspend fun createWeight(
        exerciseId: String,
        weight: Float,
        comment: String
    ): SimpleResource {
        val request = CreateWeightRequest(exerciseId, weight, comment)
        return try {
            val response = api.create(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(uiText = UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.unknownError())
            }
        } catch (e: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun getWeights(exerciseId: String): Resource<List<Weight>> {
        return try {
            val response = api.get(exerciseId)
            if (response.successful) {
                val weights = mutableListOf<Weight>()
                Resource.Success(weights)
            } else {
                response.message?.let { msg ->
                    Resource.Error(uiText = UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.unknownError())
            }
        } catch (e: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun updateWeight(
        weightId: String,
        weight: Float,
        comment: String
    ): SimpleResource {
        return try {
            val request = UpdateWeightRequest(weightId, weight, comment)
            api.update(request)
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun deleteWeight(weightId: String): SimpleResource {
        TODO("Not yet implemented")
    }
}