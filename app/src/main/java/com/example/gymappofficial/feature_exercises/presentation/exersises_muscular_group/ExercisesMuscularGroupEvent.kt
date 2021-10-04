package com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group

sealed class ExercisesMuscularGroupEvent{
    data class SwipedToDelete(val id: String): ExercisesMuscularGroupEvent()
    data class MuscularGroupSetted(val muscularGroup: String): ExercisesMuscularGroupEvent()
}
