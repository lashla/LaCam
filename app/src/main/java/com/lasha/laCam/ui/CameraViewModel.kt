package com.lasha.laCam.ui


import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){

    val allPhotos: MutableLiveData<List<Photo>> = repository.allWords.asLiveData() as MutableLiveData<List<Photo>>
    fun insertHandler(filePath: String, fileName: String){
        viewModelScope.launch {
            insertDataIntoDatabase(filePath, fileName)
        }
    }
    private suspend fun insertDataIntoDatabase(filePath: String, fileName: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPhotoData(Photo(0,filePath,fileName))
            Log.i("Insert", Photo(0,filePath,fileName).toString())
        }
    }

}