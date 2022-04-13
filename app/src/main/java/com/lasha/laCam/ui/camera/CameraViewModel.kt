package com.lasha.laCam.ui.camera


import android.util.Log
import androidx.lifecycle.*
import com.lasha.laCam.data.model.Photo
import com.lasha.laCam.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val roomRepository: RoomRepository): ViewModel(){


    fun insertHandler(filePath: String, fileName: String, fileUri: String){
        viewModelScope.launch {
            insertDataIntoDatabase(filePath, fileName, fileUri)
        }
    }
    private suspend fun insertDataIntoDatabase(filePath: String, fileName: String, fileUri: String){
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertRoom(Photo(filePath,fileName, fileUri))
            Log.i("Insert", Photo(filePath,fileName, fileUri).toString())
        }
    }

}