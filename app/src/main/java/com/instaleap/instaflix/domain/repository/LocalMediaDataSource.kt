package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface LocalMediaDataSource {
    fun getRuns(): Flow<List<Media>>
    suspend fun upsertRuns(runs: List<Media>): Result<Unit>
}