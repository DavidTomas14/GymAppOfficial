package com.example.gymappofficial.feature_exercises.presentation.add_exercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.domain.use_case.AddExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    val addExerciseUseCase: AddExerciseUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddExerciseState())
    val state: State<AddExerciseState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddExerciseEvent) {
        when (event) {
            is AddExerciseEvent.EnteredName -> {
                _state.value = state.value.copy(description = event.value)
            }
            is AddExerciseEvent.EnteredDescription -> {
                _state.value = state.value.copy(name = event.value)
            }
            is AddExerciseEvent.Create -> {
                createExercise(event.musculargroup)
            }
        }
    }

    private fun createExercise(muscularGroup: String) {
        viewModelScope.launch {
            _state.value.isLoading = true
            val response =
                addExerciseUseCase(_state.value.name, _state.value.description, muscularGroup)
            when (response) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Navigate("")
                    )
                    _state.value.isLoading = false

                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(response.uiText ?: UiText.unknownError())
                    )
                    _state.value.isLoading = false
                }
            }
        }
    }


}