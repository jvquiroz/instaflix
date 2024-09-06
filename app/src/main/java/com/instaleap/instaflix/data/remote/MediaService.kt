package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.domain.model.ResultState

interface MediaService {
    suspend fun getMedia(route: String, page: Int): ResultState<MediaResponseDto>
}
