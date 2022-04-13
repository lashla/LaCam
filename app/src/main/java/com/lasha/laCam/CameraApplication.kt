package com.lasha.laCam

import android.app.Application
import com.lasha.laCam.data.repository.RoomRepositoryImpl
import com.lasha.laCam.domain.db.GalleryDataBase
import com.lasha.laCam.domain.di.DatabaseModule
import com.lasha.laCam.domain.repository.RoomRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CameraApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DatabaseModule.provideGalleryDAO(DatabaseModule.provideDatabase(applicationContext))}
    val repository by lazy { RoomRepositoryImpl(database) }
}