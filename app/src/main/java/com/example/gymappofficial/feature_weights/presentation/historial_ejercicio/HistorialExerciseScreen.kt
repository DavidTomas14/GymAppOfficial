package com.example.gymappofficial.feature_weights.presentation.historial_ejercicio


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.core.domain.models.Weight
import com.example.gymappofficial.core.presentation.components.StandardScaffold
import com.example.gymappofficial.core.presentation.ui.theme.Beige3
import com.example.gymappofficial.core.presentation.ui.theme.Margins.horizontal
import com.example.gymappofficial.core.presentation.ui.theme.Margins.vertical
import com.example.gymappofficial.core.presentation.ui.theme.Margins.verticalLarge
import com.example.gymappofficial.core.presentation.ui.theme.PaddingLarge
import com.example.gymappofficial.core.presentation.ui.theme.PaddingMedium
import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.core.util.dateFormatter
import com.example.gymappofficial.core.util.typeFormat
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChart
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartData
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.LineChartDataModel
import com.example.gymappofficial.feature_weights.presentation.historial_ejercicio.util.transformData

@Composable
fun HistorialExerciseScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    historialExerciseViewModel: HistorialExerciseViewModel = hiltViewModel()
) {
    val showingWeight = historialExerciseViewModel.showingWeight.value
    val weights = historialExerciseViewModel.weights.value

    LaunchedEffect(key1 = true) {
        historialExerciseViewModel.getWeights()
    }

    StandardScaffold(
        navController = navController,
        showToolbar = true,
        showBottonBar = true,
        showNavIcon = false,
        toolbarTitle = "Historial",
        iconFAB = Icons.Default.Add,
        showFAB = true,
        onFabClick = {
            navController.navigate(Screen.AddWeightScreen.route + "/{${Constants.NAVARG_EXERCISE_ID}}")
        },
        state = scaffoldState
    )
    {


        Column(
            modifier = Modifier
                .padding(
                    horizontal = horizontal,
                    vertical = PaddingMedium
                )
                .fillMaxWidth()
        ) {
            LineChartRow(
                if (weights.isEmpty()) {
                    LineChartData(emptyList())
                } else
                    LineChartData(weights.transformData())
            ) {
                historialExerciseViewModel.onEvent(HistorialExerciseEvent.onClickWeight(it))
            }
            Spacer(modifier = Modifier.height(20.dp))

            showInfo(weight = showingWeight)


        }

    }
}

@Composable
fun LineChartRow(lineChartData: LineChartData, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .height(480.dp)
            .fillMaxWidth()
    ) {
        LineChart(
            onClick = {
                onClick(it)
            },
            lineChartData = lineChartData
        )
    }
}

@Composable
fun showInfo(weight: Weight) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(140.dp)
            .background(Beige3)

    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = PaddingLarge
            )
        ) {
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${weight.weight} kg",
                    style = MaterialTheme.typography.h1,
                    color = Color.Black
                )
                Text(
                    text = "${weight.date.dateFormatter(typeFormat.ALL)} kg",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
            Text(
                text = weight.comment,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
    }

    @Composable
    fun HorizontalOffsetSelector(lineChartDataModel: LineChartDataModel) {
        val selectedType = lineChartDataModel.pointDrawerType

        Row(
            modifier = Modifier.padding(
                horizontal = horizontal,
                vertical = verticalLarge
            ),
            verticalAlignment = CenterVertically
        ) {
            Text("Point Drawer")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontal, vertical = vertical)
                    .align(CenterVertically)
            ) {
                LineChartDataModel.PointDrawerType.values().forEach { type ->
                    OutlinedButton(
                        border = ButtonDefaults.outlinedBorder.takeIf { selectedType == type },
                        onClick = { lineChartDataModel.pointDrawerType = type }
                    ) {
                        Text(type.name)
                    }
                }
            }
        }
    }


}
