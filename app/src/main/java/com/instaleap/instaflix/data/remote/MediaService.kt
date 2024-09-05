package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.domain.model.Result

interface MediaService {
    //TODO: change return type to Result<T>
    suspend fun getPopularMovies(): Result<MediaResponseDto>
    suspend fun getPopularTvShows(): MediaResponseDto
    suspend fun getTopRatedMovies(): MediaResponseDto
    suspend fun getTopRatedTvShows(): MediaResponseDto
    suspend fun getNowPlayingMovies(): MediaResponseDto
    suspend fun getAiringTodayTVShows(): MediaResponseDto
}
