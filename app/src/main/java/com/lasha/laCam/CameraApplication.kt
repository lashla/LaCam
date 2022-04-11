package com.lasha.laCam

import android.app.Application
import androidx.room.Room
import com.lasha.laCam.data.db.GalleryDataBase
import com.lasha.laCam.di.RepositoryModule
import com.lasha.laCam.ui.MainRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CameraApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { RepositoryModule().getInstance(this) }
    val repository by lazy { MainRepository(database!!.galleryDao()) }
}