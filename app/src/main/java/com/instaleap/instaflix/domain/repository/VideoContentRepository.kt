package com.instaleap.instaflix.domain.repository

import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.ui.navigation.NavigationItem

interface VideoContentRepository {
    suspend fun getMedia(navigationItem: NavigationItem, page: Int): ResultState<List<VideoContent>>
}
