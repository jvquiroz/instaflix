package com.instaleap.instaflix.di

import android.util.Log
import com.instaleap.instaflix.BuildConfig
import com.instaleap.instaflix.data.local.LocalMediaDataSourceImp
import com.instaleap.instaflix.data.local.VideoContentDatabase
import com.instaleap.instaflix.data.remote.MediaService
import com.instaleap.instaflix.data.remote.RemoteMediaDataSourceImp
import com.instaleap.instaflix.data.remote.TMDBService
import com.instaleap.instaflix.data.repository.CachedMediaRepository
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import com.instaleap.instaflix.domain.repository.VideoContentRepository
import com.instaleap.instaflix.domain.repository.RemoteMediaDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class IoDispatcher

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class MainDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Dispatcher Providers
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    // Base URL
    @Provides
    @Singleton
    @Named("base_url")
    fun provideBaseUrl(): String {
        return "https://api.themoviedb.org/"
    }

    // Ktor HTTP Client Configuration
    @Provides
    @Singleton
    fun provideHttpClient(@Named("base_url") baseUrl: String): HttpClient {
        return HttpClient(Android) {
            // Base URL and Content Type
            defaultRequest {
                url(baseUrl)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            // Content Negotiation (JSON)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true // Enable for debugging (consider disabling in production)
                    ignoreUnknownKeys = true
                })
            }

            // Authentication (Bearer Token)
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = BuildConfig.TMDB_KEY,
                            refreshToken = "" // Not used in this example
                        )
                    }
                }
            }

            // Logging
            install(Logging) {
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        Log.v("Ktor Logger", message)
                    }
                }
                level = LogLevel.ALL // Change to desired logging level
            }
        }
    }

    // Services and Data Sources
    @Provides
    @Singleton
    fun provideTMDBApi(client: HttpClient): MediaService {
        return TMDBService(client)
    }

    @Provides
    @Singleton
    fun provideRemoteMediaDataSourceImp(
        service: MediaService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): RemoteMediaDataSource {
        return RemoteMediaDataSourceImp(service, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideLocalMediaDataSourceImp(
        videoContentDatabase: VideoContentDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LocalMediaDataSource {
        return LocalMediaDataSourceImp(videoContentDatabase, ioDispatcher)
    }

    // Repository
    @Provides
    @Singleton
    fun provideMediaRepository(
        localMediaDataSource: LocalMediaDataSource,
        remoteMediaDataSource: RemoteMediaDataSource
    ): VideoContentRepository {
        return CachedMediaRepository(
            localDataSource = localMediaDataSource,
            remoteDataSource = remoteMediaDataSource
        )
    }
}
