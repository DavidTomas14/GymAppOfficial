package com.example.gymappofficial.feature_auth.domain.models

import com.example.gymappofficial.core.util.Error

sealed class AuthError: Error() {
        object FieldEmpty: AuthError()
        object InvalidPassword: AuthError()
        object InputTooShort: AuthError()
        object PasswordsDontMatch: AuthError()
}
