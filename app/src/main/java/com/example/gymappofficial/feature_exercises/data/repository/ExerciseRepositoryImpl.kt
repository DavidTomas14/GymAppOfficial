package com.example.gymappofficial.feature_exercises.data.repository

import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.MuscularGroupType
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.data.models.ExerciseApiModel
import com.example.gymappofficial.feature_exercises.data.dto.request.CreateExerciseRequest
import com.example.gymappofficial.feature_exercises.data.dto.request.DeleteRequest
import com.example.gymappofficial.feature_exercises.data.dto.request.UpdateExerciseRequest
import com.example.gymappofficial.feature_exercises.data.models.toExercise
import com.example.gymappofficial.feature_exercises.data.remote.ExercisesApi
import com.example.gymappofficial.feature_exercises.domain.repository.ExercisesRepository
import okio.IOException
import retrofit2.HttpException

class ExerciseRepositoryImpl(
    private val api: ExercisesApi
) : ExercisesRepository {

    override suspend fun createExercise(
        name: String,
        description: String,
        muscularGroup: String
    ): SimpleResource {
        val request = CreateExerciseRequest(name, description, muscularGroup)
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

    override suspend fun getExercisesByMuscularGroup(muscularGroup: String): Resource<List<Exercise>> {
        return try {
            val response = api.getExercises(muscularGroup)
            if (response.successful) {
                val exercises = mutableListOf<Exercise>()
                response.data?.forEach {
                    exercises.add(it.toExercise())
                }
                Resource.Success(exercises)
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

    override suspend fun getExerciseById(exerciseId: String): Resource<Exercise> {
        return try {
            val response = api.getExerciseById(exerciseId)
            if (response.successful) {
                Resource.Success(response.data?.toExercise())
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

    override suspend fun updateExercise(
        exerciseId: String,
        description: String,
        increaseWeightComment: String,
        increasingWeightValoration: Int
    ): SimpleResource {
        return try {
            val request = UpdateExerciseRequest(
                exerciseId,
                description,
                increaseWeightComment,
                increasingWeightValoration
            )
            api.updateExercise(request)
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

    override suspend fun deleteExerciseById(
        exerciseId: String,
    ): SimpleResource {
        return try {
            val request = DeleteRequest(
                exerciseId
            )
            api.deleteExercise(request)
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

    override suspend fun getLastWeightFromExercise(exerciseId: String): Resource<Float> {
        return try {
            val response = api.getLastWeightFromExercise(exerciseId)
            Resource.Success(response.data)
        }catch (e: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun getMaxWeightFromExercise(exerciseId: String): Resource<Float> {
        return try {
            val response = api.getMaxWeightFromExercise(exerciseId)
            Resource.Success(response.data)
        }catch (e: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

}