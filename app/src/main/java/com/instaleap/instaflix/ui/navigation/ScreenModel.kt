package com.instaleap.instaflix.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenRoute(val route: String) {
    data object Movies : ScreenRoute("movies")
    data object MoviesNowPlaying : ScreenRoute("movies_now_playing")
    data object MoviesPopular : ScreenRoute("movies_popular")
    data object MoviesTopRated : ScreenRoute("movies_top_rated")
    data object TvShows : ScreenRoute("tv_shows")
    data object TvShowsNowPlaying : ScreenRoute("tv_shows_now_playing")
    data object TvShowsTopRated : ScreenRoute("tv_shows_top_rated")
    data object TvShowsPopular : ScreenRoute("tv_shows_popular")
    data object Default : ScreenRoute("default_route")
}

data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val children: List<NavigationItem> = emptyList()
)
