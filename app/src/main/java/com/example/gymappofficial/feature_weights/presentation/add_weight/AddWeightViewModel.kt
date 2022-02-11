package com.example.gymappofficial.feature_weights.presentation.add_weight

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_weights.domain.use_case.CreateWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWeightViewModel @Inject constructor(
    val addWeightUseCase: CreateWeightUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddWeightState())
    val state: State<AddWeightState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddWeightEvent) {
        val exerciseId =
            savedStateHandle.get<String>(Constants.NAVARG_EXERCISE_ID) ?: ""
        when (event) {
            is AddWeightEvent.EnteredComment -> {
                _state.value = state.value.copy(comment = event.value)
            }
            is AddWeightEvent.EnteredWeight -> {
                _state.value = state.value.copy(weight = event.value)
            }
            is AddWeightEvent.Create -> {
                createWeight(exerciseId)
            }
        }
    }

    private fun createWeight(exerciseId: String) {
        viewModelScope.launch {
            _state.value.isLoading = true
            val response =
                addWeightUseCase(exerciseId, _state.value.weight.toFloat(), _state.value.comment)
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