package com.example.gymappofficial.core.domain.models

import java.util.*

data class Ejercicio(
    val id: String = UUID.randomUUID().toString(),
    val usuario: String,
    val grupoMuscular: String,
    val nombre: String,
    val descripcion: String,
    val listaPesos: List<Float>,
    val listaComentarios: List<String>,
    val listaFechas: List<String>,
    val comentarioSubidaPeso: String,
    val subidaPeso: Int = 1,
    val isSynced: Boolean= false
)
