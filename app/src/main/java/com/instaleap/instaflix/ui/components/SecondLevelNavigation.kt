package com.instaleap.instaflix.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instaleap.instaflix.ui.navigation.NavigationItem
import com.instaleap.instaflix.ui.navigation.ScreenRoute

@Composable
fun SecondLevelNavigation(
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
                ScreenWithBottomNavigation(
                    navigationRoot = item,
                    onMenuClick = onMenuClick
                )
            }
        }
    }
}
