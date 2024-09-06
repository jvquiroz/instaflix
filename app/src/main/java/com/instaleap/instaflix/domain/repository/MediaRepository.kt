package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.Media
import com.instaleap.instaflix.domain.model.ResultState

interface MediaRepository {
    suspend fun getMedia(route: String, page: Int): ResultState<List<Media>>
}
