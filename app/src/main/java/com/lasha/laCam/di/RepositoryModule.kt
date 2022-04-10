package com.lasha.laCam.di
import android.content.Context
import androidx.room.Room
import com.lasha.laCam.data.db.GalleryDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    private var INSTANCE: GalleryDataBase? = null
    fun getInstance(context: Context): GalleryDataBase? {
        if(INSTANCE == null){
            synchronized(GalleryDataBase::class){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    GalleryDataBase::class.java,
                    "mydb"
                ).build()
            }
        }
        fun destroyInstance() {
            INSTANCE = null
        }
        return INSTANCE
    }
}
