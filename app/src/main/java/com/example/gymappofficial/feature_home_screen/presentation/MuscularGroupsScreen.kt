package com.example.gymappofficial.feature_home_screen.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.core.domain.models.MuscularGroupUi
import com.example.gymappofficial.core.presentation.ui.theme.*
import java.text.SimpleDateFormat
import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.MuscularGroupType
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.presentation.util.standardQuadFromTo
import timber.log.Timber

@Composable
fun GruposMuscularesScreen(
    navController: NavController,
    muscularGroupsViewModel: MuscularGroupsViewModel = hiltViewModel(),
) {
    val muscularGroupsState = muscularGroupsViewModel.muscularGroupsState.value

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            MotivationalGreeting(
                name = muscularGroupsState.qualifying,
                muscularGroupsState.motivationalPhrase
            )
            MuscularGroupSection(
                navController = navController,
                MuscularGroups = listOf(
                    MuscularGroupUi(
                        type = MuscularGroupType.Biceps,
                        R.drawable.ic_brazo,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Back,
                        R.drawable.ic_espalda,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Triceps,
                        R.drawable.ic_brazo,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Chest,
                        R.drawable.ic_pecho,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Shoulders,
                        R.drawable.ic_hombro,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Legs,
                        R.drawable.ic_piernas,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Abdominales,
                        R.drawable.ic_abdominales,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    MuscularGroupUi(
                        type = MuscularGroupType.Measurements,
                        R.drawable.ic_corazon,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    )
                )
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun MotivationalGreeting(
    name: String,
    phrase: String
) {

    Box(
        modifier = Modifier
            .padding(PaddingSmall)
            .clip(RoundedCornerShape(10.dp))
            .background(LightRed)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            val dateFormat = SimpleDateFormat("kk")
            val date = dateFormat.format(System.currentTimeMillis())
            Timber.d(date)

            if (date.toInt() >= 12) {
                Text(
                    text = "Buenas tardes,!",
                    style = MaterialTheme.typography.h2
                )
            } else {
                Text(
                    text = "Buenos días, $name!",
                    style = MaterialTheme.typography.h2
                )
            }

            Text(
                text = phrase,
                style = MaterialTheme.typography.body2
            )

        }
    }
}

@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MuscularGroupSection(
    MuscularGroups: List<MuscularGroupUi>,
    navController: NavController,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Grupos Musculares",
            modifier = Modifier.padding(PaddingSmall),
            style = MaterialTheme.typography.h2
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 7.5.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(MuscularGroups.size) {
                MuscularGroupItem(muscularGroup = MuscularGroups[it]) { muscularGroup ->
                    navController.navigate(Screen.MuscularGroupExercisesScreen.route + "/" + muscularGroup)
                }
            }
        }
    }
}

@Composable
fun MuscularGroupItem(
    muscularGroup: MuscularGroupUi,
    onClick: (String) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(PaddingSmall / 2)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.large)
            .background(muscularGroup.darkColor)
            .clickable {
                onClick(muscularGroup.type.route)
            }
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = muscularGroup.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = muscularGroup.lightColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
        ) {
            Text(
                text = muscularGroup.type.route,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = muscularGroup.iconId),
                tint = Color.Black,
                contentDescription = muscularGroup.type.route,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(50.dp)
            )
        }

    }
}