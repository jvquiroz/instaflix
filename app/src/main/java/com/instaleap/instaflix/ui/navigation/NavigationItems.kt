package com.instaleap.instaflix.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.instaleap.instaflix.R
import com.instaleap.instaflix.domain.model.VideoContentType
import com.instaleap.instaflix.ui.theme.*
import kotlinx.serialization.Serializable

@Composable
fun navigationItems(): List<NavigationItem> {
    return listOf(
        // Movies Section
        NavigationItem(
            title = stringResource(R.string.movies),
            route = ScreenRoute.Movies.route,
            selectedIcon = Icons.Filled.Movies,
            unselectedIcon = Icons.Outlined.Movies,
            mediaType = VideoContentType.MOVIE,
            children = listOf(
                NavigationItem(
                    title = stringResource(R.string.popular),
                    route = ScreenRoute.MoviesPopular.route,
                    selectedIcon = Icons.Filled.Popular,
                    unselectedIcon = Icons.Outlined.Popular,
                    mediaType = VideoContentType.MOVIE,
                ),
                NavigationItem(
                    title = stringResource(R.string.top_rated),
                    route = ScreenRoute.MoviesTopRated.route,
                    selectedIcon = Icons.Filled.TopRated,
                    unselectedIcon = Icons.Outlined.TopRated,
                    mediaType = VideoContentType.MOVIE,
                ),
                NavigationItem(
                    title = stringResource(R.string.now_playing),
                    route = ScreenRoute.MoviesNowPlaying.route,
                    selectedIcon = Icons.Filled.NowPlaying,
                    unselectedIcon = Icons.Outlined.NowPlaying,
                    mediaType = VideoContentType.MOVIE,
                )
            )
        ),

        // TV Shows Section
        NavigationItem(
            title = stringResource(R.string.tv_shows),
            route = ScreenRoute.TvShows.route,
            selectedIcon = Icons.Filled.Tv,
            unselectedIcon = Icons.Outlined.Tv,
            mediaType = VideoContentType.TV_SHOW,
            children = listOf(
                NavigationItem(
                    title = stringResource(R.string.popular),
                    mediaType = VideoContentType.TV_SHOW,
                    route = ScreenRoute.TvShowsPopular.route,
                    selectedIcon = Icons.Filled.Popular,
                    unselectedIcon = Icons.Outlined.Popular
                ),
                NavigationItem(
                    title = stringResource(R.string.top_rated),
                    mediaType = VideoContentType.TV_SHOW,
                    route = ScreenRoute.TvShowsTopRated.route,
                    selectedIcon = Icons.Filled.TopRated,
                    unselectedIcon = Icons.Outlined.TopRated
                ),
                NavigationItem(
                    title = stringResource(R.string.airing_today),
                    route = ScreenRoute.TvShowsNowPlaying.route,
                    mediaType = VideoContentType.TV_SHOW,
                    selectedIcon = Icons.Filled.OnAirToday,
                    unselectedIcon = Icons.Outlined.OnAirToday
                )
            )
        )
    )
}

@Serializable
sealed class ScreenRoute(val route: String) {
    object Movies : ScreenRoute("movies")
    object MoviesNowPlaying : ScreenRoute("movies_now_playing")
    object MoviesPopular : ScreenRoute("movies_popular")
    object MoviesTopRated : ScreenRoute("movies_top_rated")
    object TvShows : ScreenRoute("tv_shows")
    object TvShowsNowPlaying : ScreenRoute("tv_shows_now_playing")
    object TvShowsTopRated : ScreenRoute("tv_shows_top_rated")
    object TvShowsPopular : ScreenRoute("tv_shows_popular")
    object Default : ScreenRoute("default_route")

    @Serializable
    data class DetailsRoute(
        val id: Int,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val poster: String,
        val backdrop: String? = null
    ) : ScreenRoute("details")
}