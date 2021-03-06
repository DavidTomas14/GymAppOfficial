package com.example.gymappofficial.core.data.dto.response

class BasicApiResponse<T>(
    val successful: Boolean,
    val message: String? = null,
    val data: T? = null
) {
}