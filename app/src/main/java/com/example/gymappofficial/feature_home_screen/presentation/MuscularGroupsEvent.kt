package com.example.gymappofficial.feature_home_screen.presentation

sealed class MuscularGroupsEvent {
    data class MotivationalPhraseChanged(val value: String) : MuscularGroupsEvent()
    data class QualifierChanged(val value: String) : MuscularGroupsEvent()
}
