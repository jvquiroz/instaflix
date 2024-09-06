package com.instaleap.instaflix.data.repository


import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.model.VideoContentType
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import com.instaleap.instaflix.domain.repository.VideoContentRepository
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import com.instaleap.instaflix.ui.navigation.NavigationItem
import javax.inject.Inject

class CachedMediaRepository @Inject constructor(
    private val localDataSource: LocalMediaDataSource,
    private val remoteDataSource: RemoteMediaDataSource,
) : VideoContentRepository {

    override suspend fun getMedia(navigationItem: NavigationItem, page: Int): ResultState<List<VideoContent>> {
        return if (navigationItem.mediaType == VideoContentType.TV_SHOW) {
            fetchTvShows(navigationItem, page)
        } else {
            fetchMovies(navigationItem, page)
        }
    }

    private suspend fun fetchMovies(navigationItem: NavigationItem, page: Int): ResultState<List<VideoContent>> {
        return when (val result = remoteDataSource.getMovies(navigationItem.route, page)) {
            is ResultState.Failure -> ResultState.Failure(result.error)
            is ResultState.Success -> ResultState.Success(result.data.results.map { item ->
                item.toMedia()
            })
        }
    }

    private suspend fun fetchTvShows(navigationItem: NavigationItem, page: Int): ResultState<List<VideoContent>> {
        return when (val result = remoteDataSource.getTvShows(navigationItem.route, page)) {
            is ResultState.Failure -> ResultState.Failure(result.error)
            is ResultState.Success -> ResultState.Success(result.data.results.map { item ->
                item.toMedia()
            })
        }
    }
}