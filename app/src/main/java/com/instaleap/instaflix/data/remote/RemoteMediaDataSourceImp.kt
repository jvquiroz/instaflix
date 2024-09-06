package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.di.IoDispatcher
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteMediaDataSourceImp @Inject constructor(
    private val service: MediaService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteMediaDataSource {

    override suspend fun getMovies(route: String, page: Int): ResultState<MoviesResponseDto> {
        return withContext(ioDispatcher) {
            service.getMovies(route, page)
        }
    }

    override suspend fun getTvShows(route: String, page: Int): ResultState<TvShowsResponseDto> {
        return withContext(ioDispatcher) {
            service.getTvShows(route, page)
        }
    }
}