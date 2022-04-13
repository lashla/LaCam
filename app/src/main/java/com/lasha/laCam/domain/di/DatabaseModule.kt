package com.lasha.laCam.domain.di

import android.content.Context
import androidx.room.Room
import com.lasha.laCam.domain.db.GalleryDao
import com.lasha.laCam.domain.db.GalleryDataBase
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
    fun provideDatabase(@ApplicationContext context: Context): GalleryDataBase {
        return Room.databaseBuilder(
            context,
            GalleryDataBase::class.java,
            GalleryDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGalleryDAO(galleryDataBase: GalleryDataBase): GalleryDao {
        return galleryDataBase.galleryDao()
    }
}