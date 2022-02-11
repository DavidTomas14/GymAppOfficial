package com.example.gymappofficial.feature_exercises.presentation.add_exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.core.presentation.components.StandardTextField
import com.example.gymappofficial.core.presentation.ui.theme.Gray
import com.example.gymappofficial.core.presentation.ui.theme.PaddingMedium
import com.example.gymappofficial.core.presentation.ui.theme.PaddingSmall
import com.example.gymappofficial.core.presentation.ui.theme.Shapes
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.presentation.ui.util.asString
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddExerciseScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    addExerciseViewModel: AddExerciseViewModel = hiltViewModel(),
    muscularGroup: String?
) {
    val state = addExerciseViewModel.state.value
    val eventFlow = addExerciseViewModel.eventFlow
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvent.Navigate -> {
                    navController.navigateUp()
                }
            }

        }
    }
    Column(
        modifier = Modifier
            .padding(PaddingSmall)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.addEjercicio),
            style = MaterialTheme.typography.h2
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        StandardTextField(
            modifierTextField = Modifier.clip(Shapes.medium),
            maxLength = 30,
            color = Gray,
            hint = stringResource(id = R.string.nombre),
            text = state.name,
            onValueChange = {
                addExerciseViewModel.onEvent(AddExerciseEvent.EnteredName(it))
            }
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            StandardTextField(
                modifierTextField = Modifier
                    .height(200.dp)
                    .clip(Shapes.medium),
                modifierText = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopStart),
                maxLines = 5,
                color = Gray,
                hint = stringResource(id = R.string.decripcion),
                text = state.description,
                onValueChange = {
                    addExerciseViewModel.onEvent(AddExerciseEvent.EnteredDescription(it))
                }
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Button(
            onClick = {
                val muscularGroup = muscularGroup
                addExerciseViewModel.onEvent(AddExerciseEvent.Create(muscularGroup ?: ""))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(id = R.string.guardar),
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
    }
}