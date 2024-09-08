package com.instaleap.instaflix.ui.components

import com.instaleap.instaflix.domain.model.VideoContent

data class MediaUI(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String? = null
)

fun VideoContent.toMediaUI(): MediaUI {
    return MediaUI(
        id = this.id,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        poster = this.poster,
        backdrop = this.backdrop
    )
}