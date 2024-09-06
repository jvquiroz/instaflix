package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.R
import com.instaleap.instaflix.domain.model.Media
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaResponseDto(
    val page: Int = 0,
    val results: List<MediaItemDto> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0
)

@Serializable
data class MediaItemDto(
    val id: Int,
    val title: String? = null,
    val name: String? = null,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double
) {
    fun toMedia() = Media(
        id = id,
        title = if(title?.isNotEmpty() == true) {
            title
        } else if (name?.isNotEmpty() == true) {
            name
        } else {
            "Unknown"
        },
        poster = posterPath
    )
}