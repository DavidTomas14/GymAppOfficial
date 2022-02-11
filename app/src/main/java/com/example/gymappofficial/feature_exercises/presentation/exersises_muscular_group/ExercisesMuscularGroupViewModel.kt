package com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.util.Constants.NAVARG_MUSCULAR_GROUP
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_exercises.domain.use_case.DeleteExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExercisesByMuscularGroupUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetLastWeightFromExercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesMuscularGroupViewModel @Inject constructor(
    val getExercisesByMuscularGroupUseCase: GetExercisesByMuscularGroupUseCase,
    val getLastWeightFromExercise: GetLastWeightFromExercise,
    val deleteExerciseByIdUseCase: DeleteExerciseByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exercises = mutableStateOf<List<Exercise>>(emptyList())
    val exercise: State<List<Exercise>> = _exercises

    private val _weightsList = mutableStateOf<List<Float>>(emptyList())
    val weightslist: State<List<Float>> = _weightsList

    private val _state = mutableStateOf(LoadingState())
    val state: State<LoadingState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getExercises()
    }

    fun getExercises() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val muscularGroup = savedStateHandle.get<String>(NAVARG_MUSCULAR_GROUP) ?: ""
            val response = getExercisesByMuscularGroupUseCase(muscularGroup)
            when (response) {
                is Resource.Success -> {
                    _exercises.value = response.data ?: emptyList()
                    delay(1000)
                    getWeights()
                    _state.value = state.value.copy(isLoading = false)
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.SnackBarEvent(response.uiText ?: UiText.unknownError())
                    )
                    _state.value = state.value.copy(isLoading = false)
                }

            }
        }
    }
    private fun getWeights(){
        viewModelScope.launch {
            val weights = mutableListOf<Float>()
            _exercises.value.forEach {
               when(val response = getLastWeightFromExercise(it.id)){
                   is Resource.Success -> {
                       weights.add(response.data!!)
                   }
                   else -> {
                       weights.add(0f)
                   }
               }
            }
            _weightsList.value = weights
        }
    }
    private fun deleteExercise(exerciseId: String){
        viewModelScope.launch {
            deleteExerciseByIdUseCase(exerciseId)
            delay(1000)
            getExercises()
        }
    }

    fun onEvent(event: ExercisesMuscularGroupEvent) {
        when (event) {
            is ExercisesMuscularGroupEvent.SwipedToDelete -> {
                deleteExercise(event.id)
            }
            is ExercisesMuscularGroupEvent.Navigate -> {
            }
        }
    }
}