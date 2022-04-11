package com.lasha.laCam.ui


import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){


    fun insertHandler(filePath: String, fileName: String, fileUri: String){
        viewModelScope.launch {
            insertDataIntoDatabase(filePath, fileName, fileUri)
        }
    }
    private suspend fun insertDataIntoDatabase(filePath: String, fileName: String, fileUri: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPhotoData(Photo(filePath,fileName, fileUri))
            Log.i("Insert", Photo(filePath,fileName, fileUri).toString())
        }
    }
    var allPhotos = MutableLiveData<List<Photo>>()
    fun observe(){
        viewModelScope.launch {
            repository.allWords.collect {
                if (it.isNotEmpty()){
                    allPhotos.value = it
                    Log.i("AllPhotosData", allPhotos.value.toString())
                }
            }
        }
    }


}