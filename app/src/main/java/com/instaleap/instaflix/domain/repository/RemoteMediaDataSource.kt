package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.data.remote.MoviesResponseDto
import com.instaleap.instaflix.data.remote.TvShowsResponseDto
import com.instaleap.instaflix.domain.model.ResultState

interface RemoteMediaDataSource {
    suspend fun getMovies(route: String, page: Int): ResultState<MoviesResponseDto>
    suspend fun getTvShows(route: String, page: Int): ResultState<TvShowsResponseDto>
}
