package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Constants.NAVARG_EXERCISE_ID
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_weights.domain.use_case.GetWeightsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialExerciseViewModel @Inject constructor(
    private val getWeightsUseCase: GetWeightsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weights = mutableStateOf<List<Weight>>(emptyList())
    val weights: State<List<Weight>> = _weights

    private val _showingWeight = mutableStateOf(
        Weight(
            "",
            "",
            14f,
            "Eres un cacas no levantas nah. Te animo a que te pongas a tope fiera!!",
            907225247924,
            ""
        )
    )
    val showingWeight: State<Weight> = _showingWeight

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean?> = _isLoading

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getWeights()
    }

    fun onEvent(event: HistorialExerciseEvent) {
        when (event) {
            is HistorialExerciseEvent.NavigateToAdd -> {
            }
            is HistorialExerciseEvent.onClickWeight -> {
                _showingWeight.value = _weights.value[event.index]
            }
        }
    }

    fun getWeights() {
        viewModelScope.launch {
            _isLoading.value = true
            val exerciseId =
                savedStateHandle.get<String>(NAVARG_EXERCISE_ID) ?: ""
            val response = getWeightsUseCase(exerciseId)
            when (response) {
                is Resource.Success -> {
                    _weights.value = response.data!!
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(response.uiText ?: UiText.unknownError())
                    )
                    _isLoading.value = false
                }
            }
        }
    }
}
