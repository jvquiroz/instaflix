package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.domain.model.ErrorState
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.ui.navigation.ScreenRoute
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

    private fun getRoute(route: String): String {
        return when (route) {
            ScreenRoute.MoviesPopular.route -> {
                Routes.POPULAR_MOVIES
            }
            ScreenRoute.MoviesTopRated.route -> {
                Routes.TOP_RATED_MOVIES
            }
            ScreenRoute.MoviesNowPlaying.route -> {
                Routes.NOW_PLAYING_MOVIES
            }
            ScreenRoute.TvShowsPopular.route -> {
                Routes.POPULAR_TV_SHOWS
            }
            ScreenRoute.TvShowsTopRated.route -> {
                Routes.TOP_RATED_TV_SHOWS
            }
            ScreenRoute.TvShowsNowPlaying.route -> {
                Routes.AIRING_TODAY_TV_SHOWS
            }
            else -> {
                Routes.POPULAR_MOVIES
            }
        }
    }

    override suspend fun getMovies(route: String, page: Int): ResultState<MoviesResponseDto> {
        return try {
            client.get {
                url(getRoute(route))
                parameter("page", page)
            }.body<MoviesResponseDto>().let {
                ResultState.Success(it)
            }
        } catch(e: RedirectResponseException) {
            handleException(e)
        } catch(e: ClientRequestException) {
            handleException(e)
        } catch(e: ServerResponseException) {
            handleException(e)
        } catch(e: Exception) {
            handleException(e)
        }
    }

    override suspend fun getTvShows(route: String, page: Int): ResultState<TvShowsResponseDto> {
        return try {
            client.get {
                url(getRoute(route))
                parameter("page", page)
            }.body<TvShowsResponseDto>().let {
                ResultState.Success(it)
            }
        } catch(e: RedirectResponseException) {
            handleException(e)
        } catch(e: ClientRequestException) {
            handleException(e)
        } catch(e: ServerResponseException) {
            handleException(e)
        } catch(e: Exception) {
            handleException(e)
        }
    }

    private fun handleException(e: Exception): ResultState.Failure {
        println("Error: ${e.message}")
        return ResultState.Failure(ErrorState.UNKNOWN)
    }
}
