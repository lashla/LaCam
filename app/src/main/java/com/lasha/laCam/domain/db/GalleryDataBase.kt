package com.lasha.laCam.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lasha.laCam.data.model.Photo

@Database(entities = [Photo::class], version = 3, exportSchema = false)
abstract class GalleryDataBase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
    companion object{
        val DATABASE_NAME = "gallery_db"
    }
}

