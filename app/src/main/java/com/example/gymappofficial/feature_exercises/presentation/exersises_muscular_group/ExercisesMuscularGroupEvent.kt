package com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group

sealed class ExercisesMuscularGroupEvent{
    object Navigate : ExercisesMuscularGroupEvent()
    data class SwipedToDelete(val id: String): ExercisesMuscularGroupEvent()
}
