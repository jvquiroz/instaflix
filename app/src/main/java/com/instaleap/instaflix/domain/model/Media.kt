package com.instaleap.instaflix.domain.model

data class Media(val id: Int,
                 val title: String,
                 val poster: String)

sealed class ScreenRoute(val route: String) {
    object Movies : ScreenRoute("movies")
    object MoviesNowPlaying : ScreenRoute("movies_now_playing")
    object MoviesPopular : ScreenRoute("movies_popular")
    data object MoviesTopRated : ScreenRoute("movies_top_rated")
    data object TvShows : ScreenRoute("tv_shows")
    data object TvShowsNowPlaying : ScreenRoute("tv_shows_now_playing")
    data object TvShowsTopRated : ScreenRoute("tv_shows_top_rated")
    data object TvShowsPopular : ScreenRoute("tv_shows_popular")
    data object Default : ScreenRoute("default_route")
}

