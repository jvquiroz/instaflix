package com.instaleap.instaflix.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.instaleap.instaflix.R
import com.instaleap.instaflix.domain.model.ScreenRoute
import com.instaleap.instaflix.ui.navigation.NavigationItem
import com.instaleap.instaflix.ui.theme.Movies
import com.instaleap.instaflix.ui.theme.NowPlaying
import com.instaleap.instaflix.ui.theme.OnAirToday
import com.instaleap.instaflix.ui.theme.Popular
import com.instaleap.instaflix.ui.theme.TopRated
import com.instaleap.instaflix.ui.theme.Tv

@Composable
fun navigationItems(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = stringResource(R.string.movies),
            route = ScreenRoute.Movies.route,
            selectedIcon = Icons.Filled.Movies,
            unselectedIcon = Icons.Outlined.Movies,
            children = listOf(
                NavigationItem(
                    title = stringResource(R.string.popular),
                    route = ScreenRoute.MoviesPopular.route,
                    selectedIcon = Icons.Filled.Popular,
                    unselectedIcon = Icons.Outlined.Popular
                ),
                NavigationItem(
                    title = stringResource(R.string.top_rated),
                    route = ScreenRoute.MoviesTopRated.route,
                    selectedIcon = Icons.Filled.TopRated,
                    unselectedIcon = Icons.Outlined.TopRated
                ),
                NavigationItem(
                    title = stringResource(R.string.now_playing),
                    route = ScreenRoute.MoviesNowPlaying.route,
                    selectedIcon = Icons.Filled.NowPlaying,
                    unselectedIcon = Icons.Outlined.NowPlaying
                )
            )
        ),
        NavigationItem(
            title = stringResource(R.string.tv_shows),
            route = ScreenRoute.TvShows.route,
            selectedIcon = Icons.Filled.Tv,
            unselectedIcon = Icons.Outlined.Tv,
            children = listOf(
                NavigationItem(
                    title = stringResource(R.string.popular),
                    route = ScreenRoute.TvShowsPopular.route,
                    selectedIcon = Icons.Filled.Popular,
                    unselectedIcon = Icons.Outlined.Popular
                ),
                NavigationItem(
                    title = stringResource(R.string.top_rated),
                    route = ScreenRoute.TvShowsTopRated.route,
                    selectedIcon = Icons.Filled.TopRated,
                    unselectedIcon = Icons.Outlined.TopRated
                ),
                NavigationItem(
                    title = stringResource(R.string.airing_today),
                    route = ScreenRoute.TvShowsNowPlaying.route,
                    selectedIcon = Icons.Filled.OnAirToday,
                    unselectedIcon = Icons.Outlined.OnAirToday
                )
            )
        )
    )
}