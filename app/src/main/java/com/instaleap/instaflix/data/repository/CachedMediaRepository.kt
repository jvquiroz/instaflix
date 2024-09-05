package com.instaleap.instaflix.data.repository

import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import com.instaleap.instaflix.domain.repository.MediaRepository
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import kotlinx.coroutines.flow.Flow

class CachedMediaRepository(
    private val localDataSource: LocalMediaDataSource,
    private val remoteDataSource: RemoteMediaDataSource
) : MediaRepository {

    override fun getPopularMovies(): Flow<List<Media>> {
        TODO("Not yet implemented")
    }
}