package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.di.IoDispatcher
import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteMediaDataSourceImp @Inject constructor(
    private val service: MediaService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteMediaDataSource {

    override suspend fun getMedia(route: String, page: Int): ResultState<List<Media>> {
        return withContext(ioDispatcher) {
            when (val result = service.getMedia(route, page)) {
                is ResultState.Failure -> ResultState.Failure(result.error)
                is ResultState.Success -> ResultState.Success(result.data.results.map { item ->
                    item.toMedia()
                })
            }
        }
    }
}