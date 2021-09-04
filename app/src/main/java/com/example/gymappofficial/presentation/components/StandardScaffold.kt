package com.example.gymappofficial.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymappofficial.R
import com.example.gymappofficial.domain.models.BottomNavItem
import com.example.gymappofficial.presentation.ui.theme.CursorBotones
import com.example.gymappofficial.presentation.ui.theme.LightGray
import com.example.gymappofficial.presentation.ui.util.Screen




@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBottonBar: Boolean = false,
    showFAB: Boolean = false,
    iconFAB: ImageVector? = null,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.InfoEjercicioScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.HistorialEjercicioScreen.route,
            icon = Icons.Filled.Timeline,
            contentDescription = "Historial"
        )
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
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
    ) {
        content()
    }
}