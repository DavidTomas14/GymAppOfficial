package com.example.gymappofficial.core.presentation.components

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
import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.core.util.Screen


@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    showSnackBar: Boolean = true,
    navController: NavController,
    showBottonBar: Boolean = false,
    state: ScaffoldState,
    showToolbar: Boolean = false,
    toolbarTitle: String? = null,
    showFAB: Boolean = false,
    showNavIcon: Boolean = true,
    iconFAB: ImageVector? = null,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.ExerciseInfoScreen.route + "/{${Constants.NAVARG_EXERCISE_ID}}",
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.ExerciseHistoryScreen.route + "/{${Constants.NAVARG_EXERCISE_ID}}",
            icon = Icons.Filled.Timeline,
            contentDescription = "Historial"
        )
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
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
                        if (showNavIcon)
                        IconButton(onClick = {
                           navController.popBackStack()
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
                                when(navController.currentDestination?.route){
                                     Screen.ExerciseInfoScreen.route + "/{${Constants.NAVARG_EXERCISE_ID}}" ->{
                                         navController.navigate(Screen.ExerciseHistoryScreen.route + "/{${Constants.NAVARG_EXERCISE_ID}}")
                                     }
                                    else-> {
                                        navController.popBackStack()
                                    }
                                }
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
            if (showSnackBar){
                SnackbarHost(it){data->
                    Snackbar(
                        contentColor = TextWhite,
                        backgroundColor = DarkGray,
                        snackbarData = data
                    )
                }
            }
        }
    ) {
        content()
    }
}