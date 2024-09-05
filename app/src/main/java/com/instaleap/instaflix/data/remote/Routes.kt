package com.instaleap.instaflix.data.remote

object Routes {
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR_MOVIES = "$BASE_URL/movie/popular?language=en-US&page=1"
}