package com.example.gymappofficial.feature_exercises.presentation.exersises_muscular_group

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.core.domain.models.Exercise
import com.example.gymappofficial.core.presentation.ui.theme.*
import com.example.gymappofficial.R
import com.example.gymappofficial.core.presentation.components.StandardScaffold
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.presentation.ui.util.asString
import com.example.gymappofficial.core.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ExercisesMuscularGroupScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    exercisesMuscularGroupViewModel: ExercisesMuscularGroupViewModel = hiltViewModel(),
    muscularGroup: String?
) {
    val exercises = exercisesMuscularGroupViewModel.exercise.value
    val context = LocalContext.current
    val weightsList = exercisesMuscularGroupViewModel.weightslist.value
    val isLoading = exercisesMuscularGroupViewModel.state.value.isLoading

    LaunchedEffect(key1 = true) {
        exercisesMuscularGroupViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        exercisesMuscularGroupViewModel.getExercises()
    }

    StandardScaffold(
        navController = navController,
        showToolbar = true,
        toolbarTitle = muscularGroup ?: "",
        state = scaffoldState
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddExerciseScreen.route + "/$muscularGroup")
                    },
                    backgroundColor = CursorBotones,
                ) {
                    Icon(
                        imageVector = Default.Add,
                        contentDescription = stringResource(id = R.string.addEjercicio),
                        tint = Color.Black
                    )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.End,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(DeepBlue)
                    .padding()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                    )
                }
            }

            Column {
                Spacer(modifier = Modifier.height(PaddingMedium))
                ListaEjercicios(
                    exercises = exercises,
                    navController = navController,
                    weightsList = weightsList,
                    onSwipeEvent = {
                        exercisesMuscularGroupViewModel.onEvent(
                            ExercisesMuscularGroupEvent.SwipedToDelete(
                                it
                            )
                        )
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListaEjercicios(
    exercises: List<Exercise>,
    navController: NavController,
    weightsList: List<Float>,
    onSwipeEvent: (String) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(exercises) { index, item ->
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd) {
                        onSwipeEvent(item.id)
                    }
                    it != DismissValue.DismissedToEnd
                }
            )

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(StartToEnd),
                dismissThresholds =  { FractionalThreshold(0.65f) },
                background = {

                    val color by animateColorAsState(
                        when (dismissState.dismissDirection) {
                        StartToEnd -> Color.Red
                        EndToStart -> Color.Transparent
                        null -> Color.Transparent
                    }
                    )
                    Box(
                        modifier = Modifier
                            .padding(
                                PaddingValues(
                                    start = PaddingSmall,
                                    end = PaddingSmall,
                                    top = PaddingSmall / 2f
                                )
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .background(color)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Default.Delete,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(100.dp)
                                .padding(PaddingSmall)
                        )
                    }
                },
                dismissContent = {
                    EjercicioItem(
                        exercise = exercises[index],
                        weight = if (weightsList.isEmpty()) 0f else weightsList[index],
                        color =
                        if (index == 0 || index % 4 == 0) {
                            BlueViolet2
                        } else if (index == 1 || (index - 1) % 4 == 0) {
                            OrangeYellow2
                        } else if (index == 2 || (index - 2) % 4 == 0) {
                            Beige2
                        } else if (index == 3 || (index - 3) % 4 == 0) {
                            LightGreen1
                        } else {
                            TextWhite
                        },
                        onClick = {
                            navController.navigate(Screen.ExerciseInfoScreen.route + "/${exercises[index].id}")
                        }
                    )
                }
            )
        }


    }
}


@Composable
fun EjercicioItem(
    exercise: Exercise,
    weight: Float,
    color: Color,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                PaddingValues(
                    start = PaddingSmall,
                    end = PaddingSmall,
                    top = PaddingSmall / 2f
                )
            )
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .fillMaxWidth()
            .clickable { onClick(exercise.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = exercise.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 18.dp)
        )
        Row() {
            Text(
                text = "$weight kg",
                color = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(LightGray)
                    .padding(vertical = 5.dp, horizontal = 15.dp),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.width(15.dp))
        }

        /* Image(
             painter = painterResource(id = if (exercise.isSynced) R.drawable.ic_check else R.drawable.ic_cross),
             contentDescription = "Icon Sync",
             modifier = Modifier
                 .padding(start = 9.dp, end = 23.dp)
         )*/
    }
}