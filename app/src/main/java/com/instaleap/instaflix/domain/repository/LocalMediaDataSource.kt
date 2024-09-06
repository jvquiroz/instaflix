package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.model.ResultState
import kotlinx.coroutines.flow.Flow

interface LocalMediaDataSource {
    fun getRuns(): Flow<List<Media>>
    suspend fun upsertRuns(runs: List<Media>): ResultState<Unit>
}