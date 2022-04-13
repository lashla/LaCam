package com.lasha.laCam.domain.di

import com.lasha.laCam.data.repository.RoomRepositoryImpl
import com.lasha.laCam.domain.db.GalleryDao
import com.lasha.laCam.domain.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun proidesRoomRepository(galleryDao: GalleryDao): RoomRepository {
        return RoomRepositoryImpl(galleryDao)
    }
}
