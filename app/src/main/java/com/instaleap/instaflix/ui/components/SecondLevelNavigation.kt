package com.instaleap.instaflix.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instaleap.instaflix.domain.model.ScreenRoute
import com.instaleap.instaflix.ui.navigation.NavigationItem

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
                    title = item.title,
                    navigationItems = item.children,
                    onMenuClick = onMenuClick
                )
            }
        }
    }
}
