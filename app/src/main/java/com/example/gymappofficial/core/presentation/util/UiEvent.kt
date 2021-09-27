package com.example.gymappofficial.core.presentation.util

import com.example.gymappofficial.core.util.UiText

sealed class UiEvent {
    data class SnackBarEvent(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}