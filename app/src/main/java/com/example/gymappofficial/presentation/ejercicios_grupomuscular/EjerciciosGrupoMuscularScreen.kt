package com.example.gymappofficial.presentation.ejercicios_grupomuscular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.core.domain.models.Ejercicio
import com.example.gymappofficial.core.presentation.ui.theme.*
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.components.StandardScaffold
import com.example.gymappofficial.core.util.Screen

@Composable
fun EjerciciosGrupoMuscularScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    ejerciciosGrupoMuscularViewModel: EjerciciosGrupoMuscularViewModel = hiltViewModel(),
    grupoMuscular: String?
) {
    StandardScaffold(
        navController = navController,
        showToolbar = true,
        toolbarTitle = grupoMuscular,
        state = scaffoldState
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddExerciseScreen.route)
                    },
                    backgroundColor = CursorBotones,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.addEjercicio),
                        tint = Color.Black
                    )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.End,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DeepBlue)
                    .padding()
            )

            Column {
                Spacer(modifier = Modifier.height(PaddingMedium))
                ListaEjercicios(
                    ejercicios = listOf(
                        Ejercicio(
                            id = "1",
                            "David",
                            "Biceps",
                            "Banco de Scott",
                            "Ejercicio de brazos muy bueno",
                            listOf(54.5f, 52.6f),
                            listOf("8 repes soy un toro", "6 repes me he muerto"),
                            listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                            comentarioSubidaPeso = "Estoy ready",
                            subidaPeso = 1,
                            isSynced = true
                        ),
                        Ejercicio(
                            id = "2",
                            "David",
                            "Biceps",
                            "Press de Banca",
                            "Ejercicio de brazos muy bueno",
                            listOf(54.5f, 52.6f),
                            listOf("8 repes soy un toro", "6 repes me he muerto"),
                            listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                            comentarioSubidaPeso = "Estoy ready",
                            subidaPeso = 1,
                            isSynced = true
                        ),
                        Ejercicio(
                            id = "3",
                            "David",
                            "Biceps",
                            "Curl de Biceps",
                            "Ejercicio de brazos muy bueno",
                            listOf(54.5f, 52.6f),
                            listOf("8 repes soy un toro", "6 repes me he muerto"),
                            listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                            comentarioSubidaPeso = "Estoy ready",
                            subidaPeso = 1,
                            isSynced = true
                        ),
                        Ejercicio(
                            id = "4",
                            "David",
                            "Biceps",
                            "Pulldown" +
                                    "",
                            "Ejercicio de brazos muy bueno",
                            listOf(54.5f, 52.6f),
                            listOf("8 repes soy un toro", "6 repes me he muerto"),
                            listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                            comentarioSubidaPeso = "Estoy ready",
                            subidaPeso = 1,
                            isSynced = true
                        ),
                    ), navController = navController
                )
            }
        }
    }

}

@Composable
fun ListaEjercicios(
    ejercicios: List<Ejercicio>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(ejercicios.size) { index ->
            EjercicioItem(
                ejercicio = ejercicios[index],
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
                    navController.navigate(Screen.ExerciseInfoScreen.route + "/${ejercicios[index].id}")
                }
            )
        }
    }
}

@Composable
fun EjercicioItem(
    ejercicio: Ejercicio,
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
            .clickable { onClick(ejercicio.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ejercicio.nombre,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 18.dp)

        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${ejercicio.listaPesos.maxOrNull().toString()} kg",
                color = Color.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(LightGray)
                    .padding(vertical = 5.dp, horizontal = 8.dp),
                style = MaterialTheme.typography.body1

            )
            Image(
                painter = painterResource(id = if (ejercicio.isSynced) R.drawable.ic_check else R.drawable.ic_cross),
                contentDescription = "Icon Sync",
                modifier = Modifier
                    .padding(start = 9.dp, end = 23.dp)
            )
        }
    }
}