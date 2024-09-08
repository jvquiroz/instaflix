package com.instaleap.instaflix.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoContentDao {

    @Query("SELECT * FROM VideoContentEntity WHERE type = :route")
    suspend fun getMoviesByType(route: String): List<VideoContentEntity>

    @Insert
    fun insertAll(vararg users: VideoContentEntity)

    @Query("DELETE FROM VideoContentEntity WHERE type = :route")
    suspend fun deleteAll(route: String)
}