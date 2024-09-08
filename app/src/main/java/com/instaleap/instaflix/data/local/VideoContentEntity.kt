package com.instaleap.instaflix.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.instaleap.instaflix.domain.model.VideoContent

@Entity(primaryKeys = ["id", "type"])
data class VideoContentEntity(
    val id: Int,
    val title: String,
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val poster: String,
    val type: String,
    val backdrop: String? = null
) {
    fun toVideoContent() = VideoContent(
        id = this.id,
        title = this.title,
        overview = this.overview,
        poster = this.poster,
        backdrop = this.backdrop,
        releaseDate = this.releaseDate,
    )
}

fun VideoContent.toEntity(type: String): VideoContentEntity {
    return VideoContentEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        poster = this.poster,
        backdrop = this.backdrop,
        type = type,
    )
}