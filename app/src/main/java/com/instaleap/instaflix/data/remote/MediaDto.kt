package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.domain.model.VideoContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val page: Int = 0,
    val results: List<MovieDto> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0
)

@Serializable
data class TvShowsResponseDto(
    val page: Int = 0,
    val results: List<TvShowDto> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0
)

@Serializable
data class TvShowDto(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("first_air_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String? = null
) {
    fun toMedia() = VideoContent(
        id = id,
        title = name,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        poster = posterPath
    )
}

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String? = null
) {
    fun toMedia() = VideoContent(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        poster = posterPath
    )
}
