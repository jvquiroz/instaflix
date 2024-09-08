package com.instaleap.instaflix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VideoContentEntity::class], version = 1)
abstract class VideoContentDatabase : RoomDatabase() {
    abstract fun videoContentDao(): VideoContentDao
}