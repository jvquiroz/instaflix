package com.instaleap.instaflix.ui.components

import com.instaleap.instaflix.domain.model.VideoContent

data class MediaUI(
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val poster: String,
    val backdrop: String? = null
)

fun VideoContent.toMediaUI(): MediaUI {
    return MediaUI(
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        poster = this.poster,
        backdrop = this.backdrop
    )
}