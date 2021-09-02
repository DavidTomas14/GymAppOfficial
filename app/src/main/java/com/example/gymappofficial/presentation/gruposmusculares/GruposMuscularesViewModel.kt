package com.example.gymappofficial.presentation.gruposmusculares

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GruposMuscularesViewModel @Inject constructor() : ViewModel() {

    private val _usuario = mutableStateOf("David")
    val usuario: State<String> = _usuario

    private val _fraseMotivadora = mutableStateOf("")
    val fraseMotivadora: State<String> = _fraseMotivadora


    fun setUsuario(usuarioText: String) {
        _usuario.value = usuarioText
    }
   fun setFraseMotivadora(fraseText: String) {
       _fraseMotivadora.value = fraseText
   }


}