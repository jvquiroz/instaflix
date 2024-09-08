package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.data.local.VideoContentEntity
import com.instaleap.instaflix.domain.model.VideoContent

interface LocalMediaDataSource {
    suspend fun getVideoContent(route: String): List<VideoContentEntity>
    suspend fun upsertVideoContent(items: List<VideoContent>, page: Int, route: String)
}