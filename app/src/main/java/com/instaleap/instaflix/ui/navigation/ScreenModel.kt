package com.instaleap.instaflix.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.instaleap.instaflix.domain.model.VideoContentType


data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val mediaType: VideoContentType,
    val children: List<NavigationItem> = emptyList()
)
