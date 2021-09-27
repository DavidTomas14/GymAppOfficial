package com.example.gymappofficial.feature_auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.presentation.util.UiEvent
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

     fun onEvent(event: SplashEvent) {
         when(event){
             is SplashEvent.Authenticate -> {
                 viewModelScope.launch{
                     when(authenticateUseCase()) {
                         is Resource.Error -> {
                             print("Error")
                             _eventFlow.emit(
                                 UiEvent.Navigate(Screen.LoginScreen.route)
                             )

                         }
                         is Resource.Success -> {
                             print("Success")
                             _eventFlow.emit(
                                 UiEvent.Navigate(Screen.MuscularGroupScreen.route)
                             )
                         }
                     }
                 }
             }
         }
     }
}