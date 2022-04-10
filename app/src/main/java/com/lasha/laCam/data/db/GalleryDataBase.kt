package com.lasha.laCam.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lasha.laCam.data.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class GalleryDataBase: RoomDatabase() {
    abstract val galleryDao: GalleryDao

    companion object{
        @Volatile
        private var INSTANCE: GalleryDataBase? = null

        fun getInstance(context: Context): GalleryDataBase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        GalleryDataBase::class.java,
                        "gallery database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}

