package com.instaleap.instaflix.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Movies : Screen("movies")
    data object MoviesNowPlaying : Screen("movies_now_playing")
    data object MoviesPopular : Screen("movies_popular")
    data object MoviesTopRated : Screen("movies_top_rated")
    data object TvShows : Screen("tv_shows")
    data object TvShowsNowPlaying : Screen("tv_shows_now_playing")
    data object TvShowsTopRated : Screen("tv_shows_top_rated")
    data object TvShowsPopular : Screen("tv_shows_popular")
    data object Default : Screen("default_route")
}

data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val children: List<NavigationItem> = emptyList()
)
