package com.example.gymappofficial.feature_weights.presentation.add_weight

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
import androidx.compose.ui.text.input.KeyboardType
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
fun AddWeightScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    addWeightViewModel: AddWeightViewModel = hiltViewModel(),
) {
    val state = addWeightViewModel.state.value
    val eventFlow = addWeightViewModel.eventFlow
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
                    navController.popBackStack()
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
            text = stringResource(id = R.string.addWeight),
            style = MaterialTheme.typography.h2
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        StandardTextField(
            modifierTextField = Modifier.clip(Shapes.medium),
            maxLength = 30,
            color = Gray,
            keyboardType = KeyboardType.Number,
            hint = stringResource(id = R.string.weight),
            text = state.weight,
            onValueChange = {
                addWeightViewModel.onEvent(AddWeightEvent.EnteredWeight(it))
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
                hint = stringResource(id = R.string.comment),
                text = state.comment,
                onValueChange = {
                    addWeightViewModel.onEvent(AddWeightEvent.EnteredComment(it))
                }
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Button(
            onClick = {
                addWeightViewModel.onEvent(AddWeightEvent.Create)
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