package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.model.ResultState
import kotlinx.coroutines.flow.Flow

interface LocalMediaDataSource {
    fun getRuns(): Flow<List<VideoContent>>
    suspend fun upsertRuns(runs: List<VideoContent>): ResultState<Unit>
}