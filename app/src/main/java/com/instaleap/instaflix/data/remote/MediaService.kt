package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.domain.model.ResultState

interface MediaService {
    suspend fun getMovies(route: String, page: Int): ResultState<MoviesResponseDto>

    suspend fun getTvShows(route: String, page: Int): ResultState<TvShowsResponseDto>
}