package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio

sealed class HistorialExerciseEvent {
    object NavigateToAdd: HistorialExerciseEvent()
    class OnClickFilter(): HistorialExerciseEvent()
    class onClickWeight(val index : Int): HistorialExerciseEvent()

}
