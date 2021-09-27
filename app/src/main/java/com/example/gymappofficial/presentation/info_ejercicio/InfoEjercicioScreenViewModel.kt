package com.example.gymappofficial.presentation.info_ejercicio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gymappofficial.core.domain.models.Ejercicio
import javax.inject.Inject

class InfoEjercicioScreenViewModel @Inject constructor() : ViewModel() {

    private val _ejercicio = mutableStateOf<Ejercicio>(
        Ejercicio(
            id = "1",
            "David",
            "Biceps",
            "Banco de Scott",
            "Ejercicio de biceps sentado en un banco",
            listOf(54f,14f,15f),
            listOf("Sobrao", "Sobrao", "Sobrao"),
            listOf("10-1-2021","10-1-2021","10-1-2021"),
            comentarioSubidaPeso = "Animo crack que puedes subir sobraaaaao",
            subidaPeso = 1,
        )
    )
    val ejercicio: State<Ejercicio> = _ejercicio

   fun setEjercicio(ejercicio: Ejercicio) {
       _ejercicio.value = ejercicio
   }


}