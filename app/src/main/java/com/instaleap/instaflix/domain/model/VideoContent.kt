package com.instaleap.instaflix.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoContent(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String? = null,
)

enum class VideoContentType {
    MOVIE,
    TV_SHOW
}
