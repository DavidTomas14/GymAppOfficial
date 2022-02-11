package com.example.gymappofficial.feature_exercises.presentation.exercise_info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Constants.NAVARG_EXERCISE_ID
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetMaxWeightFromExercise
import com.example.gymappofficial.feature_exercises.domain.use_case.UpdateExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoExerciseViewModel @Inject constructor(
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val getMaxWeightFromExercise: GetMaxWeightFromExercise,
    private val updateExerciseUseCase: UpdateExerciseUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exercise = mutableStateOf<Exercise?>(null)
    val exercise: State<Exercise?> = _exercise

    private val _maxWeight = mutableStateOf(0f)
    val maxWeight: State<Float> = _maxWeight

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean?> = _isLoading

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        getExerciseById()
        getMaxWeight()

    }

    private fun getExerciseById() {
        viewModelScope.launch {
            val exerciseId = savedStateHandle.get<String>(NAVARG_EXERCISE_ID) ?: ""
            val response = getExerciseByIdUseCase(exerciseId)
            when (response) {
                is Resource.Success -> {
                    _exercise.value = response.data!!
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(response.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }
    private fun getMaxWeight() {
        viewModelScope.launch {
            val exerciseId = savedStateHandle.get<String>(NAVARG_EXERCISE_ID) ?: ""
            val response = getMaxWeightFromExercise(exerciseId)
            when (response) {
                is Resource.Success -> {
                    _maxWeight.value = response.data!!
                }
            }
        }
    }

    fun onEvent(event: InfoExerciseEvents) {
        when (event) {
            is InfoExerciseEvents.ChangedIncreseWeightValoration -> {
                _exercise.value = _exercise.value?.copy(
                    increasingWeightValoration = event.value
                )
            }
            is InfoExerciseEvents.EnteredDescription -> {
                _exercise.value = _exercise.value?.copy(
                    description = event.value
                )
            }
            is InfoExerciseEvents.EnteredIncreaseWeightComment -> {
                _exercise.value = _exercise.value?.copy(
                    inscreaseWeightComment = event.value
                )
            }
            is InfoExerciseEvents.UpdateExercise -> {
                viewModelScope.launch {
                    val exercise = _exercise.value ?: return@launch
                    when(updateExerciseUseCase(exercise)){
                        is Resource.Success -> {
                            _eventFlow.emit(
                                UiEvent.SnackBarEvent(UiText.StringResource(R.string.exercise_successfully_updated))
                            )
                        }
                        is Resource.Error->
                            _eventFlow.emit(
                                UiEvent.SnackBarEvent(UiText.unknownError())

                            )
                    }
                }
            }
        }
    }
}
