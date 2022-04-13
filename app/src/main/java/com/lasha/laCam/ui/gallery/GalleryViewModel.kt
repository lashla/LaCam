package com.lasha.laCam.ui.gallery

import android.util.Log
import androidx.lifecycle.*
import com.lasha.laCam.data.model.Photo
import com.lasha.laCam.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val roomRepository: RoomRepository): ViewModel() {

    var allPhotos = MutableLiveData<List<Photo>>()
    init {
        allPhotos = MutableLiveData<List<Photo>>()
        getAllData()
    }
    private fun getAllData(){
       viewModelScope.launch(Dispatchers.IO) {
           fetchPhotoData()
       }
    }

    private fun fetchPhotoData(){
        viewModelScope.launch(Dispatchers.Main) {
            roomRepository.getAllRoomData().let {
                if (it.isNotEmpty()) {
                    allPhotos.value = it
                    Log.i("AllPhotosData", allPhotos.value.toString())
                }
            }
        }
    }

}