package com.instaleap.instaflix.data.repository


import com.instaleap.instaflix.di.IoDispatcher
import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import com.instaleap.instaflix.domain.repository.MediaRepository
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CachedMediaRepository @Inject constructor(
    private val localDataSource: LocalMediaDataSource,
    private val remoteDataSource: RemoteMediaDataSource,
   @IoDispatcher private val ioDispatcher: CoroutineContext
) : MediaRepository {

    override suspend fun getMedia(route: String, page: Int): ResultState<List<Media>> {
        return withContext(ioDispatcher) {
            when (val result = remoteDataSource.getMedia(route, page)) {
                is ResultState.Failure -> ResultState.Failure(result.error)
                is ResultState.Success -> ResultState.Success(result.data)
            }

        }
    }
}