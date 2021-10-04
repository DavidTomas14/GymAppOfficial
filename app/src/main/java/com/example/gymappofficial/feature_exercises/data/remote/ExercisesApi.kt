package com.example.gymappofficial.feature_exercises.data.remote

import com.example.gymappofficial.core.data.dto.response.BasicApiResponse
import com.example.gymappofficial.core.util.QueryParams.PARAM_EXERCISE_ID
import com.example.gymappofficial.core.util.QueryParams.PARAM_MUSCULAR_GROUP
import com.example.gymappofficial.feature_exercises.data.models.ExerciseApiModel
import com.example.gymappofficial.feature_exercises.data.dto.request.CreateExerciseRequest
import com.example.gymappofficial.feature_exercises.data.dto.request.DeleteRequest
import com.example.gymappofficial.feature_exercises.data.dto.request.UpdateExerciseRequest
import okhttp3.internal.http.hasBody
import retrofit2.http.*

interface ExercisesApi {

    @POST("/api/exercise/create")
    suspend fun create(
        @Body request: CreateExerciseRequest
    ): BasicApiResponse<Unit>

    @GET("/api/exercise/getexercises")
    suspend fun getExercises(
        @Query(PARAM_MUSCULAR_GROUP) muscularGroupType:String
    ): BasicApiResponse<List<ExerciseApiModel>>

    @GET("/api/exercise/getexercisebyId")
    suspend fun getExerciseById(
        @Query(PARAM_EXERCISE_ID) exerciseId:String
    ): BasicApiResponse<ExerciseApiModel>

    @POST("/api/exercise/update")
    suspend fun updateExercise(
        @Body request: UpdateExerciseRequest
    ): BasicApiResponse<Unit>

    @HTTP(method = "DELETE", path = "/api/exercise/delete", hasBody = true)
    suspend fun deleteExercise(
        @Body request: DeleteRequest
    ): BasicApiResponse<Unit>

    companion object{
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}