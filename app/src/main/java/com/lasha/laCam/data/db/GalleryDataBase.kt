package com.lasha.laCam.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lasha.laCam.data.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class GalleryDataBase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}

