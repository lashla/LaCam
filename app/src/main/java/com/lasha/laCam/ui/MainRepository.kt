package com.lasha.laCam.ui

import com.lasha.laCam.data.db.GalleryDao
import com.lasha.laCam.data.model.Photo
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class MainRepository @Inject  constructor(private val galleryDao: GalleryDao){
    fun  insertPhotoData(photo: Photo) = galleryDao.insert(photo)

    fun  fetchSortedPhotos() = galleryDao.getDateSortedPhotos()
}