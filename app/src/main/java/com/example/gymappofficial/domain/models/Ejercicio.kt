package com.example.gymappofficial.domain.models

data class Ejercicio(
    val usuario: String,
    val grupoMuscular: String,
    val nombre: String,
    val listaPesos: List<Float>,
    val listaComentarios: List<String>,
    val listaFechas: List<String>,
    val isSynced: Boolean= false
)
