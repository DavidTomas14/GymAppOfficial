package com.example.gymappofficial.core.domain.models

sealed class MuscularGroupType(val route: String) {
    object Biceps: MuscularGroupType("Biceps")
    object Back: MuscularGroupType("Back")
    object Triceps: MuscularGroupType("Triceps")
    object Chest: MuscularGroupType("Chest")
    object Shoulders: MuscularGroupType("Shoulders")
    object Legs: MuscularGroupType("Legs")
    object Abdominales: MuscularGroupType("Abdominales")
    object Measurements: MuscularGroupType("Measurements")
}

fun String.toMuscularGroup(): MuscularGroupType? {
    when(this){
        "Biceps" -> return MuscularGroupType.Biceps
        "Back" -> return MuscularGroupType.Back
        "Triceps" -> return MuscularGroupType.Triceps
        "Chest" -> return MuscularGroupType.Chest
        "Shoulders" -> return MuscularGroupType.Shoulders
        "Legs" -> return MuscularGroupType.Legs
        "Measurements" -> return MuscularGroupType.Measurements
        else-> return null
    }
}
