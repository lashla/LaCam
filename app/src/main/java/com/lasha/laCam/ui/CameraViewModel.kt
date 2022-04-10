package com.lasha.laCam.ui


import androidx.lifecycle.*
import androidx.room.Room
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){

    var allPhotos = MutableLiveData<List<Photo>>()

    fun insertHandler(filePath: String, fileName: String){
        viewModelScope.launch {
            insertDataIntoDatabase(filePath, fileName)
        }
    }
    private suspend fun insertDataIntoDatabase(filePath: String, fileName: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPhotoData(Photo(0,filePath,fileName))
        }
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            allPhotos = repository.fetchSortedPhotos().asLiveData() as MutableLiveData<List<Photo>>
        }
    }
}