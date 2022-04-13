package com.lasha.laCam.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lasha.laCam.data.model.Photo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao{

    @Query("SELECT * FROM photos")
    suspend fun getAll(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM photos ORDER BY file_name ASC")
    suspend fun getDateSortedPhotos(): List<Photo>
}

