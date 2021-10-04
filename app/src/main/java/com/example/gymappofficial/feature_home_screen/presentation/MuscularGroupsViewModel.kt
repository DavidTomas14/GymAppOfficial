package com.example.gymappofficial.feature_home_screen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gymappofficial.core.presentation.util.UiEvent
import com.example.gymappofficial.feature_home_screen.util.Constants
import com.example.gymappofficial.feature_home_screen.util.Constants.MOTIVATIONAL_PHRASES
import com.example.gymappofficial.feature_home_screen.util.Constants.QUALIFIERS_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MuscularGroupsViewModel @Inject constructor(

) : ViewModel() {

    private val _muscularGroupsState = mutableStateOf(MuscularGroupsState())
    val muscularGroupsState: State<MuscularGroupsState> = _muscularGroupsState

    init {
        val randomPhrase = Random.nextInt(from = 0, until = MOTIVATIONAL_PHRASES.size - 1)
        val randomQualifier = Random.nextInt(from = 0, until = QUALIFIERS_LIST.size - 1)
        _muscularGroupsState.value = _muscularGroupsState.value.copy(
            motivationalPhrase = MOTIVATIONAL_PHRASES[randomPhrase]
        )
        _muscularGroupsState.value = _muscularGroupsState.value.copy(
            qualifying = QUALIFIERS_LIST[randomQualifier]
        )
    }
}