package com.example.gymappofficial.feature_exercises.presentation.exercise_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.core.presentation.components.StandardScaffold
import com.example.gymappofficial.core.presentation.ui.theme.*
import com.example.gymappofficial.core.presentation.ui.util.UiEvent
import com.example.gymappofficial.core.presentation.ui.util.asString
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@Composable
fun InfoEjercicioScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    infoEjercicioScreenViewModel: InfoExerciseViewModel = hiltViewModel()
) {
    val exercise = infoEjercicioScreenViewModel.exercise.value ?: return
    val isLoading = infoEjercicioScreenViewModel.isLoading.value ?: false
    val maxWeight = infoEjercicioScreenViewModel.maxWeight.value.toString()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        infoEjercicioScreenViewModel.eventFlow.collectLatest { event ->
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
    StandardScaffold(
        navController = navController,
        showSnackBar = false,
        showBottonBar = true,
        iconFAB =  Icons.Default.Edit,
        showToolbar = true,
        showFAB = true,
        toolbarTitle = exercise.name,
        state = scaffoldState,
        onFabClick = {
            infoEjercicioScreenViewModel.onEvent(InfoExerciseEvents.UpdateExercise)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
                .background(DeepBlue)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column {
                    TextoDescripcion(
                        descripcion = exercise.description,
                        onValueChange = {
                            infoEjercicioScreenViewModel.onEvent(InfoExerciseEvents.EnteredDescription(it))
                        }
                    )
                    Spacer(modifier = Modifier.height(PaddingMedium))
                    TextMaxWeight(maxWeight) //ejercicio.listaPesos.maxOfOrNull{it}.toString()
                    Spacer(modifier = Modifier.height(PaddingMedium))
                    SubidaPeso(
                        exercise.increasingWeightValoration,
                        exercise.inscreaseWeightComment,
                        onSubidaPesoChanged = {
                            infoEjercicioScreenViewModel.onEvent(InfoExerciseEvents.ChangedIncreseWeightValoration(it))
                        },
                        onComentarioPesoChanged = {
                            infoEjercicioScreenViewModel.onEvent(InfoExerciseEvents.EnteredIncreaseWeightComment(it))
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun TextoDescripcion(
    descripcion: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = descripcion,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(150.dp)
            .background(Gray),
        textStyle = MaterialTheme.typography.body1,
        maxLines = 7,
    )
    Timber.d(descripcion)
}

@Composable
fun TextMaxWeight(
    maxPeso: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MarronPesoActual)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Max Weight",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            text = "$maxPeso kg",
            style = TextStyle(fontSize = 50.sp, color = TextWhite),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun SubidaPeso(
    subidaDePeso: Int,
    comentarioSubidaDePeso: String,
    onSubidaPesoChanged: (Int) -> Unit,
    onComentarioPesoChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(fondoSubidaPeso)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Increasing Weight",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(if (subidaDePeso == 1) com.example.gymappofficial.R.drawable.ic_sad else R.drawable.ic_sad_white),
                contentDescription = "Icon Sad",
                modifier = Modifier
                    .padding(start = 55.dp, end = 65.dp)
                    .clickable {
                        onSubidaPesoChanged(1)
                    }
                    .size(50.dp)
            )
            Image(
                painter = painterResource(if (subidaDePeso == 2) R.drawable.ic_poker_face else R.drawable.ic_poker_face_white),
                contentDescription = "Icon Poker Face",
                modifier = Modifier
                    .clickable {
                        onSubidaPesoChanged(2)
                    }
                    .size(50.dp)
            )
            Image(
                painter = painterResource(if (subidaDePeso == 3) R.drawable.ic_happy else R.drawable.ic_happy_white),
                contentDescription = "Icon Happy",
                modifier = Modifier
                    .padding(start = 65.dp, end = 55.dp)
                    .clickable {
                        onSubidaPesoChanged(3)
                    }
                    .size(50.dp)
            )
        }
        TextField(
            value = comentarioSubidaDePeso,
            label = {
                Text(
                    text = "Comments...",
                    style = MaterialTheme.typography.body2,
                    color = LightGray
                )
            },
            modifier = Modifier
                .padding(horizontal = 35.dp, vertical = 15.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White),
            onValueChange = {
                onComentarioPesoChanged(it)
            },
            textStyle = TextStyle(Color.Black)
        )
    }
}
