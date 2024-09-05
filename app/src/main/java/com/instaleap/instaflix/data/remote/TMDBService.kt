package com.instaleap.instaflix.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class TMDBService @Inject constructor(
    private val client: HttpClient
) : MediaService {
    override suspend fun getPopularMovies(): MediaResponseDto {
        return try {
            client.get {
                url(Routes.POPULAR_MOVIES)
                //parameter("api_key", BuildConfig.API_KEY)
            }.body()
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            MediaResponseDto()
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            MediaResponseDto()
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            MediaResponseDto()
        } catch(e: Exception) {
            println("Error: ${e.message}")
            MediaResponseDto()
        }
    }

    override suspend fun getPopularTvShows(): MediaResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): MediaResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedTvShows(): MediaResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(): MediaResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getAiringTodayTVShows(): MediaResponseDto {
        TODO("Not yet implemented")
    }
}