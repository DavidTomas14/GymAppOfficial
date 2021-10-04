package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.presentation.util.UiEvent
import com.example.gymappofficial.core.util.Constants.NAVARG_EXERCISE_ID
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.presentation.exercise_info.InfoExerciseEvents
import com.example.gymappofficial.feature_weights.domain.use_case.GetWeightsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialEjercicioViewModel @Inject constructor(
    private val getWeightsUseCase: GetWeightsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weights = mutableStateOf<List<Weight>>(emptyList())
    val weights : State<List<Weight>> = _weights

    private val _isLoading = mutableStateOf(false)
    val isLoading : State<Boolean?> = _isLoading

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getWeights()
    }


    private fun getWeights(){
        viewModelScope.launch {
            val exerciseId = savedStateHandle.get<String>(NAVARG_EXERCISE_ID) ?: ""
            val response = getWeightsUseCase(exerciseId)
            when(response){
                is Resource.Success->{
                    _weights.value = response.data!!
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(response.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }

   /* fun onEvent(event: InfoExerciseEvents){
        when(event) {
            is InfoExerciseEvents.ChangedIncreseWeightValoration ->{
                _exercise.value = _exercise.value?.copy(
                    increasingWeightValoration = event.value
                )
            }
            is InfoExerciseEvents.EnteredDescription -> {
                _exercise.value = _exercise.value?.copy(
                    description = event.value
                )
            }
            is InfoExerciseEvents.EnteredIncreaseWeightComment-> {
                _exercise.value = _exercise.value?.copy(
                    inscreaseWeightComment = event.value
                )
            }
        }
    }*/
}
