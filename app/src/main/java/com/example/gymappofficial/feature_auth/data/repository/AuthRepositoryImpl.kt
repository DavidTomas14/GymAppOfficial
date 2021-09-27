package com.example.gymappofficial.feature_auth.data.repository

import com.example.gymappofficial.R
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.SimpleResource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_auth.data.dto.request.AccountAuthRequest
import com.example.gymappofficial.feature_auth.data.remote.AuthApi
import com.example.gymappofficial.feature_auth.domain.repository.AuthRepository
import okio.IOException
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun register(
        username: String,
        password: String
    ): SimpleResource {
        val request = AccountAuthRequest(username, password)
        return try {
            val response = api.register(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unkown))
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

    override suspend fun login(username: String, password: String): SimpleResource {
        val request = AccountAuthRequest(username, password)
        return try {
            val response = api.login(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unkown))
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
}