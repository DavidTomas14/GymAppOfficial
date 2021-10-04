package com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.domain.models.MuscularGroupType
import com.example.gymappofficial.core.presentation.util.UiEvent
import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.core.util.Constants.MUSCULAR_GROUP
import com.example.gymappofficial.core.util.Constants.NAVARG_MUSCULAR_GROUP
import com.example.gymappofficial.core.util.Resource
import com.example.gymappofficial.core.util.UiText
import com.example.gymappofficial.feature_auth.presentation.login.LoginState
import com.example.gymappofficial.feature_exercises.domain.use_case.DeleteExerciseByIdUseCase
import com.example.gymappofficial.feature_exercises.domain.use_case.GetExercisesByMuscularGroupUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesMuscularGroupViewModel @Inject constructor(
    val getExercisesByMuscularGroupUseCase: GetExercisesByMuscularGroupUseCase,
    val deleteExerciseByIdUseCase: DeleteExerciseByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exercises = mutableStateOf<List<Exercise>>(emptyList())
    val exercise: State<List<Exercise>> = _exercises

    private val _state = mutableStateOf(LoadingState())
    val state: State<LoadingState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getExercises()
    }

    private fun getExercises() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val muscularGroup = savedStateHandle.get<String>(NAVARG_MUSCULAR_GROUP) ?: ""
            val response = getExercisesByMuscularGroupUseCase(muscularGroup)
            when (response) {
                is Resource.Success -> {
                    _exercises.value = response.data ?: emptyList()
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
    private fun deleteExercise(exerciseId: String){
        viewModelScope.launch {
            deleteExerciseByIdUseCase(exerciseId)
            getExercises()
        }
    }

    fun onEvent(event: ExercisesMuscularGroupEvent) {
        when (event) {
            is ExercisesMuscularGroupEvent.SwipedToDelete -> {
                deleteExercise(event.id)
            }
            is ExercisesMuscularGroupEvent.MuscularGroupSetted -> {

            }
        }
    }
}