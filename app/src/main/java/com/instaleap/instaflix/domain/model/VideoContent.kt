package com.instaleap.instaflix.domain.model

data class VideoContent(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val poster: String,
    val backdrop: String? = null,
)

enum class VideoContentType {
    MOVIE,
    TV_SHOW
}

