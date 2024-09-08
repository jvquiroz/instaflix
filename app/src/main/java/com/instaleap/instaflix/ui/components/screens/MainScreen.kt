package com.instaleap.instaflix.ui.components.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instaleap.instaflix.ui.components.BottomNavigationContainer
import com.instaleap.instaflix.ui.navigation.NavigationItem
import com.instaleap.instaflix.ui.navigation.ScreenRoute

@Composable
fun MainScreen(
    navController: NavHostController,
    items: List<NavigationItem>,
    onMenuClick: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Movies.route
    ) {
        items.forEach { item ->
            composable(item.route) {
                BottomNavigationContainer(
                    navigationRoot = item,
                    onMenuClick = onMenuClick
                )
            }
        }
    }
}
