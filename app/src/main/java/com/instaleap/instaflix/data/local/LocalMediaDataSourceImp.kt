package com.instaleap.instaflix.data.local

import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import kotlinx.coroutines.flow.Flow

class LocalMediaDataSourceImp: LocalMediaDataSource {
    override fun getRuns(): Flow<List<VideoContent>> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertRuns(runs: List<VideoContent>): ResultState<Unit> {
        TODO("Not yet implemented")
    }
}