package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    fun getPopularMovies(): Flow<List<Media>>
}
