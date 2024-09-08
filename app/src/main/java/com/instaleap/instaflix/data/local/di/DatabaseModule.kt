package com.instaleap.instaflix.data.local.di

import android.content.Context
import androidx.room.Room
import com.instaleap.instaflix.data.local.VideoContentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): VideoContentDatabase {
        return Room.databaseBuilder(
            context,
            VideoContentDatabase::class.java,
            "video_content_database"
        ).build()
    }
}
