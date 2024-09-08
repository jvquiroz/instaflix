package com.instaleap.instaflix.data.repository

import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.model.VideoContentType
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import com.instaleap.instaflix.domain.repository.VideoContentRepository
import com.instaleap.instaflix.ui.navigation.NavigationItem
import javax.inject.Inject

class CachedMediaRepository @Inject constructor(
    private val localDataSource: LocalMediaDataSource,
    private val remoteDataSource: RemoteMediaDataSource,
) : VideoContentRepository {

    override suspend fun getVideoContent(
        navigationItem: NavigationItem,
        page: Int
    ): ResultState<List<VideoContent>> {
        val fetchedData = when (navigationItem.mediaType) {
            VideoContentType.MOVIE -> fetchMovies(navigationItem, page)
            VideoContentType.TV_SHOW -> fetchTvShows(navigationItem, page)
        }

        return handleResultAndCache(fetchedData, navigationItem.route, page)
    }

    private suspend fun handleResultAndCache(
        fetchedData: ResultState<List<VideoContent>>,
        route: String,
        page: Int
    ): ResultState<List<VideoContent>> {
        return when (fetchedData) {
            is ResultState.Success -> {
                localDataSource.upsertVideoContent(items = fetchedData.data, page = page, route = route)
                fetchedData
            }
            is ResultState.Failure -> {
                //requesting more pages only makes sense when the data is coming from the remote source
                if (page == 1) {
                    val cachedData = localDataSource.getVideoContent(route).map { it.toVideoContent() }
                    ResultState.Success(cachedData, true)
                } else {
                    ResultState.Failure(fetchedData.error)
                }
            }
        }
    }

    private suspend fun fetchMovies(
        navigationItem: NavigationItem,
        page: Int
    ): ResultState<List<VideoContent>> {
        return remoteDataSource.getMovies(navigationItem.route, page)
            .let { result ->
                when (result) {
                    is ResultState.Failure -> result
                    is ResultState.Success -> ResultState.Success(result.data.results.map { it.toVideoContent() })
                }
            }
    }

    private suspend fun fetchTvShows(
        navigationItem: NavigationItem,
        page: Int
    ): ResultState<List<VideoContent>> {
        return remoteDataSource.getTvShows(navigationItem.route, page)
            .let { result ->
                when (result) {
                    is ResultState.Failure -> result
                    is ResultState.Success -> ResultState.Success(result.data.results.map { it.toVideoContent() })
                }
            }
    }
}