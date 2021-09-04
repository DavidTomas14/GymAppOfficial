package com.example.gymappofficial.presentation.addejercicio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEjercicioScreenViewModel @Inject constructor() : ViewModel() {

    private val _nombreEjercico = mutableStateOf("")
    val nombreEjercicio: State<String> = _nombreEjercico

    private val _descripcionEjercicio = mutableStateOf("")
    val descripcionEjercicio: State<String> = _descripcionEjercicio

    fun setNombre(nombreText: String) {
        _nombreEjercico.value = nombreText
    }

    fun setDescripcion(descripcionTexto: String) {
        _descripcionEjercicio.value = descripcionTexto
    }

}