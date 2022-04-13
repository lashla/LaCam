package com.lasha.laCam.data.repository

import com.lasha.laCam.data.model.Photo
import com.lasha.laCam.domain.db.GalleryDao
import com.lasha.laCam.domain.repository.RoomRepository
import javax.inject.Singleton

@Singleton
class RoomRepositoryImpl(private val galleryDao: GalleryDao): RoomRepository {
    override suspend fun getAllRoomData(): List<Photo> {
        return galleryDao.getAll()
    }

    override suspend fun getSortedRoomData(): List<Photo> {
        return galleryDao.getDateSortedPhotos()
    }

    override suspend fun insertRoom(photo: Photo) {
        return galleryDao.insert(photo)
    }
}