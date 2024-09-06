package com.instaleap.instaflix.data.remote

object Routes {
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR_MOVIES = "$BASE_URL/movie/popular?language=en-US"
    const val NOW_PLAYING_MOVIES = "$BASE_URL/movie/now_playing?language=en-US"
    const val TOP_RATED_MOVIES = "$BASE_URL/movie/top_rated?language=en-US"
    const val POPULAR_TV_SHOWS = "$BASE_URL/tv/popular?language=en-US"
    const val TOP_RATED_TV_SHOWS = "$BASE_URL/tv/top_rated?language=en-US"
    const val AIRING_TODAY_TV_SHOWS = "$BASE_URL/tv/airing_today?language=en-US&sort_by=popularity.desc"
}