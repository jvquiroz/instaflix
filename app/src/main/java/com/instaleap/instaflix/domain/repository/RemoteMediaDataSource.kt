package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.Media

interface RemoteMediaDataSource {
    suspend fun getRuns(): Result<List<Media>>
}