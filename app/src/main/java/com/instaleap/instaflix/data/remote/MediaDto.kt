package com.instaleap.instaflix.data.remote

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
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("vote_average")
    val voteAverage: Double
)