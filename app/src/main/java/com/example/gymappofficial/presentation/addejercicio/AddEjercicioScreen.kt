package com.example.gymappofficial.presentation.addejercicio

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.components.StandardTextField
import com.example.gymappofficial.presentation.ui.theme.Gray
import com.example.gymappofficial.presentation.ui.theme.PaddingMedium
import com.example.gymappofficial.presentation.ui.theme.PaddingSmall
import com.example.gymappofficial.presentation.ui.theme.Shapes
import com.example.gymappofficial.presentation.ui.util.Screen

@Composable
fun AddEjercicioScreen(
    navController: NavController,
    addEjercicioScreenViewModel: AddEjercicioScreenViewModel = hiltViewModel()
) {
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
            color = Gray,
            hint = stringResource(id = R.string.nombre),
            text = addEjercicioScreenViewModel.nombreEjercicio.value,
            onValueChange = {
                addEjercicioScreenViewModel.setNombre(it)
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
                text = addEjercicioScreenViewModel.descripcionEjercicio.value,
                onValueChange = {
                    addEjercicioScreenViewModel.setDescripcion(it)
                }
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Button(
            onClick = { navController.navigate(Screen.InfoEjercicioScreen.route)},
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