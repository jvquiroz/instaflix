package com.instaleap.instaflix.ui.components

import com.instaleap.instaflix.domain.model.Media

data class MediaUI (
    val title: String,
    val overview: String,
    val poster: String
    )

fun Media.toMediaUI(): MediaUI {
    return MediaUI(
        title = title,
        poster = poster,
        overview = "overview"
    )

}