package com.example.gymappofficial.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usuario = mutableStateOf("")
    val username: State<String> = _usuario

    private val _contrasena = mutableStateOf("")
    val contrasena: State<String> = _contrasena

    fun setUsuario(usuarioText: String) {
        _usuario.value = usuarioText
    }
   fun setContrasena(contrasenaText: String) {
       _contrasena.value = contrasenaText
   }
}