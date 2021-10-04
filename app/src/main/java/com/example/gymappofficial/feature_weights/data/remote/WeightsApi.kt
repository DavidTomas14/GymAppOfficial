package com.example.gymappofficial.feature_weights.data.remote

import com.dtalonso.data.requests.DeleteRequest
import com.example.gymappofficial.core.data.dto.response.BasicApiResponse
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.util.QueryParams
import com.example.gymappofficial.core.util.QueryParams.PARAM_EXERCISE_ID
import com.example.gymappofficial.feature_exercises.data.dto.request.UpdateExerciseRequest
import com.example.gymappofficial.feature_weights.data.dto.requests.CreateWeightRequest
import com.example.gymappofficial.feature_weights.data.dto.requests.UpdateWeightRequest
import retrofit2.http.*

interface WeightsApi {

    @POST("/api/weight/create")
    suspend fun create(
        @Body weightRequest: CreateWeightRequest
    ): BasicApiResponse<Unit>

    @GET("/api/weight/get")
    suspend fun get(
        @Query(PARAM_EXERCISE_ID) exerciseId: String
    ): BasicApiResponse<List<Weight>>

    @POST("/api/weight/update")
    suspend fun update(
        @Body request: UpdateWeightRequest
    ): BasicApiResponse<Unit>

    @DELETE("/api/weight/delete")
    suspend fun delete(
        @Body request: DeleteRequest
    ): BasicApiResponse<Unit>

    companion object{
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}