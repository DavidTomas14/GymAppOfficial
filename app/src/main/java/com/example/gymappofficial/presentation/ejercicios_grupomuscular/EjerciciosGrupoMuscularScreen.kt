package com.example.gymappofficial.presentation.ejercicios_grupomuscular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.domain.models.Ejercicio
import com.example.gymappofficial.presentation.ui.theme.*
import com.example.gymappofficial.R
import timber.log.Timber

@Composable
fun EjerciciosGrupoMuscularScreen(
    navController: NavController,
    ejerciciosGrupoMuscularViewModel: EjerciciosGrupoMuscularViewModel = hiltViewModel(),
    grupoMuscular: String?
){
    val scaffoldState = rememberScaffoldState()
    Timber.d("$grupoMuscular")

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "+",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue)
                .padding()
        )

        Column {
            TituloLista(grupoMuscular = "Espalda")
            ListaEjercicios(
                ejercicios = listOf(
                    Ejercicio(
                        "David",
                        "Biceps",
                        "Banco de Scott",
                        listOf(54.5f, 52.6f),
                        listOf("8 repes soy un toro", "6 repes me he muerto"),
                        listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                        isSynced = true
                    ),
                    Ejercicio(
                        "David",
                        "Biceps",
                        "Banco de Scott",
                        listOf(54.5f, 52.6f),
                        listOf("8 repes soy un toro", "6 repes me he muerto"),
                        listOf("11:44 1-02-2021", "13:15 3-03-2021")
                    ),
                    Ejercicio(
                        "David",
                        "Biceps",
                        "Banco de Scott",
                        listOf(54.5f, 52.6f),
                        listOf("8 repes soy un toro", "6 repes me he muerto"),
                        listOf("11:44 1-02-2021", "13:15 3-03-2021"),
                        isSynced = true
                    ),
                    Ejercicio(
                        "David",
                        "Biceps",
                        "Banco de Scott",
                        listOf(54.5f, 52.6f),
                        listOf("8 repes soy un toro", "6 repes me he muerto"),
                        listOf("11:44 1-02-2021", "13:15 3-03-2021")
                    )
                )
            )
        }
    }
}

@Composable
fun TituloLista(
    grupoMuscular: String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = "Ejercicios de $grupoMuscular",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ListaEjercicios(ejercicios: List<Ejercicio>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(ejercicios.size) {
            EjercicioItem(ejercicio = ejercicios[it], color =
            if (it==0 || it % 4==0) {
                BlueViolet2
            }else if (it == 1 || (it - 1) % 4 == 0) {
                OrangeYellow2
            }else if (it == 2 || (it - 2) % 4 == 0) {
                Beige2
            }else if (it == 3 || (it - 3) % 4 == 0) {
                LightGreen1
            }else {
                TextWhite
            }

            )
        }
    }
}

@Composable
fun EjercicioItem(
    ejercicio: Ejercicio,
    color: Color,
) {
    Row(
        modifier = Modifier
            .padding( PaddingValues(start = PaddingSmall, end = PaddingSmall, top = PaddingSmall / 2f ))
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .fillMaxWidth(),
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