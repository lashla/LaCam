package com.lasha.laCam.di
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lasha.laCam.data.db.GalleryDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Volatile
    private var INSTANCE: GalleryDataBase? = null

    fun getInstance(context: Context): GalleryDataBase? {
        if(INSTANCE == null){
            synchronized(GalleryDataBase::class){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    GalleryDataBase::class.java,
                    "mydb"
                ).fallbackToDestructiveMigration()
                    .build()
                Log.i("RepositoryModule", "$INSTANCE, build")
            }
        }
        fun destroyInstance() {
            INSTANCE = null
        }
        return INSTANCE
    }
}
