package com.instaleap.instaflix.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector



data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val children: List<NavigationItem> = emptyList()
)
