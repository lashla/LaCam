package com.lasha.laCam.di

import android.content.Context
import androidx.room.Room
import com.lasha.laCam.data.db.GalleryDao
import com.lasha.laCam.data.db.GalleryDataBase
import com.lasha.laCam.ui.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideGalleyDao(@ApplicationContext appContext: Context): GalleryDao {
        return RepositoryModule().getInstance(appContext)!!.galleryDao()
    }

    @Provides
    @Singleton
    fun provideAppRepository(galleryDao: GalleryDao) = MainRepository(galleryDao)

}