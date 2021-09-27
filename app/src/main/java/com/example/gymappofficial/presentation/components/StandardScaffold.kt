package com.example.gymappofficial.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymappofficial.R
import com.example.gymappofficial.core.domain.models.BottomNavItem
import com.example.gymappofficial.core.presentation.ui.theme.*
import com.example.gymappofficial.core.util.Screen


@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBottonBar: Boolean = false,
    state: ScaffoldState,
    showToolbar: Boolean = false,
    toolbarTitle: String? = null,
    showFAB: Boolean = false,
    iconFAB: ImageVector? = null,
    grupoMuscularActual: String? = null,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.ExerciseInfoScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.ExerciseHistoryScreen.route,
            icon = Icons.Filled.Timeline,
            contentDescription = "Historial"
        )
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            if (showToolbar) {
                TopAppBar(
                    contentColor = Gray,
                    elevation = 5.dp,
                    title = {
                        if (toolbarTitle != null) {
                            Text(
                                text = toolbarTitle,
                                style = MaterialTheme.typography.h2
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.MuscularGroupExercisesScreen.route + "/" + grupoMuscularActual) {
                              popUpTo(Screen.ExerciseInfoScreen.route){inclusive = true}
                            }

                           /* when (navBackStackEntry?.destination?.route) {
                                Screen.EjerciciosGrupoMuscularScreen.route -> {
                                    navController.navigate(Screen.GruposMuscularesScreen.route)
                                    navController.popBackStack()
                                }
                                Screen.InfoEjercicioScreen.route -> {
                                    navController.navigate(Screen.EjerciciosGrupoMuscularScreen.route + "/" + grupoMuscularActual)
                                    navController.popBackStack()
                                }
                                else -> null

                            }*/
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.arrow_back
                                ),
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (showBottonBar) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = LightGray,
                    cutoutShape = CircleShape
                ) {
                    BottomNavigation {
                        bottomNavItems.forEachIndexed { index, bottomNavItem ->
                            StandardBottomNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route == navController.currentDestination?.route,
                                selectedColor = MaterialTheme.colors.primary,
                                unselectedColor = Color.Black
                            ) {
                                navController.navigate(bottomNavItem.route)
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = state,
        floatingActionButton = {
            if (showFAB) {
                FloatingActionButton(
                    onClick = onFabClick,
                    backgroundColor = CursorBotones,
                ) {
                    if (iconFAB != null) {
                        Icon(
                            imageVector = iconFAB,
                            contentDescription = stringResource(id = R.string.editar_ejercicio),
                            tint = Color.Black
                        )
                    }
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        snackbarHost = {
            SnackbarHost(it){data->
                Snackbar(
                    contentColor = TextWhite,
                    backgroundColor = DarkGray,
                    snackbarData = data
                )
            }

        }
    ) {
        content()
    }
}