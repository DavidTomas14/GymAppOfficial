package com.example.gymappofficial.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usuario = mutableStateOf("")
    val usuario: State<String> = _usuario

    private val _contrasena = mutableStateOf("")
    val contrasena: State<String> = _contrasena

    private val _isPasswordVisible = mutableStateOf(false)
    val isPasswordVisible: State<Boolean> =_isPasswordVisible

    private val _usuarioError = mutableStateOf("UserName too Long")
    val usuarioError: State<String> =_usuarioError

    private val _contrasenaError = mutableStateOf("")
    val contrasenaError: State<String> =_contrasenaError

    fun setUsuario(usuarioText: String) {
        _usuario.value = usuarioText
    }
   fun setContrasena(contrasenaText: String) {
       _contrasena.value = contrasenaText
   }

    fun setPasswordVisibility(passwordVisibility: Boolean) {
        _isPasswordVisible.value = passwordVisibility
    }

    fun setUsuarioError(error: String) {
        _usuarioError.value = error
    }

    fun setContrasenaError(error: String) {
        _contrasenaError.value = error
    }

}