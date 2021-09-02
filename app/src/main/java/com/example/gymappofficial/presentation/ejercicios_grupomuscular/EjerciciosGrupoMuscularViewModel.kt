package com.example.gymappofficial.presentation.ejercicios_grupomuscular

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EjerciciosGrupoMuscularViewModel @Inject constructor() : ViewModel() {

    private val _usuario = mutableStateOf("David")
    val usuario: State<String> = _usuario

    private val _listaEjercicios = mutableStateOf<List<String>>(emptyList())
    val listaEjercicios: State<List<String>> = _listaEjercicios


    fun setUsuario(usuarioText: String) {
        _usuario.value = usuarioText
    }

}