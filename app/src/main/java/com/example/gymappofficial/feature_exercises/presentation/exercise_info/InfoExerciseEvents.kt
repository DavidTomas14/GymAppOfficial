package com.example.gymappofficial.feature_exercises.presentation.exercise_info

sealed class InfoExerciseEvents() {
    data class EnteredDescription(val value: String) : InfoExerciseEvents()
    data class EnteredIncreaseWeightComment(val value: String) : InfoExerciseEvents()
    data class ChangedIncreseWeightValoration(val value: Int) : InfoExerciseEvents()
    object UpdateExercise : InfoExerciseEvents()
}
