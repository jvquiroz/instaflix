package com.instaleap.instaflix.data.local

import android.database.sqlite.SQLiteException
import android.util.Log
import com.instaleap.instaflix.di.IoDispatcher
import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.repository.LocalMediaDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalMediaDataSourceImp @Inject constructor(
    private val videoContentDatabase: VideoContentDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : LocalMediaDataSource {

    override suspend fun getVideoContent(route: String): List<VideoContentEntity> {
        return withContext(ioDispatcher) {
            videoContentDatabase.videoContentDao().getMoviesByType(route)
        }
    }

    override suspend fun upsertVideoContent(items: List<VideoContent>, page: Int, route: String) {
        withContext(ioDispatcher) {
            if (page == 1) {
                videoContentDatabase.videoContentDao().deleteAll(route)
            }
            try {
                videoContentDatabase.videoContentDao().insertAll(
                    *items.map { it.toEntity(route) }.toTypedArray()
                )
            } catch (exception: SQLiteException) {
                Log.w("LocalMediaDataSourceImp", "Error inserting video content", exception)
            }
        }
    }
}