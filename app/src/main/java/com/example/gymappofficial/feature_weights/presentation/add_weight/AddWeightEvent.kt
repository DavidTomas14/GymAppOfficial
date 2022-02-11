package com.example.gymappofficial.feature_weights.presentation.add_weight

sealed class AddWeightEvent{
    data class EnteredWeight(val value: String): AddWeightEvent()
    data class EnteredComment(val value: String): AddWeightEvent()
    object Create: AddWeightEvent()
}
