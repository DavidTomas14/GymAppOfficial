package com.example.gymappofficial.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _usuario = mutableStateOf("")
    val usuario: State<String> = _usuario

    private val _contrasena = mutableStateOf("")
    val constrasena: State<String> = _contrasena

    private val _contrasenaRepetida = mutableStateOf("")
    val constrasenaRepetida: State<String> = _contrasenaRepetida

    private val _isPasswordVisible = mutableStateOf(false)
    val isPasswordVisible: State<Boolean> =_isPasswordVisible

    private val _usuarioError = mutableStateOf("")
    val usuarioError : State<String> = _usuarioError

    private val _constrasenaError = mutableStateOf("Cant use special characters")
    val contrasenaError : State<String> = _constrasenaError

    fun setUsuario(usuarioText: String) {
        _usuario.value = usuarioText
    }

    fun setConstrasenaRepetida(contrasenaText: String) {
        _contrasenaRepetida.value = contrasenaText
    }

    fun setConstrasena(contrasenaText: String) {
        _contrasena.value = contrasenaText
    }
    fun setPasswordVisibility(passwordVisibility: Boolean) {
        _isPasswordVisible.value = passwordVisibility
    }
    fun setUsuarioError(error: String) {
        _usuarioError.value = error
    }
    fun setContrasenaError(error: String) {
        _constrasenaError.value = error
    }


}