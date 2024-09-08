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

    private val routes = object {
        val POPULAR_MOVIES = "/3/movie/popular"
        val TOP_RATED_MOVIES = "/3/movie/top_rated"
        val NOW_PLAYING_MOVIES = "/3/movie/now_playing"
        val POPULAR_TV_SHOWS = "/3/tv/popular"
        val TOP_RATED_TV_SHOWS = "/3/tv/top_rated"
        val AIRING_TODAY_TV_SHOWS = "/3/tv/airing_today"
    }

    private fun getRoute(route: String): String {
        return when (route) {
            ScreenRoute.MoviesPopular.route -> routes.POPULAR_MOVIES
            ScreenRoute.MoviesTopRated.route -> routes.TOP_RATED_MOVIES
            ScreenRoute.MoviesNowPlaying.route -> routes.NOW_PLAYING_MOVIES
            ScreenRoute.TvShowsPopular.route -> routes.POPULAR_TV_SHOWS
            ScreenRoute.TvShowsTopRated.route -> routes.TOP_RATED_TV_SHOWS
            ScreenRoute.TvShowsNowPlaying.route -> routes.AIRING_TODAY_TV_SHOWS
            else -> routes.POPULAR_MOVIES
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
        } catch (e: Exception) {
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
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun handleException(exception: Exception)  : ResultState.Failure {
        return when (exception) {
            is ServerResponseException,
            is RedirectResponseException,
            is ClientRequestException -> {
                ResultState.Failure(ErrorState.INTERNET_CONNECTION)
            } else ->  ResultState.Failure(ErrorState.UNKNOWN)
        }
    }
}
