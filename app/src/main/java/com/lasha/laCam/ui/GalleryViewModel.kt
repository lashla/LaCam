package com.lasha.laCam.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    var allPhotos = MutableLiveData<List<Photo>>()

    fun getData(): List<Photo>? {
        viewModelScope.launch(Dispatchers.Main) {
            allPhotos = repository.fetchSortedPhotos().asLiveData() as MutableLiveData<List<Photo>>
        }
        return allPhotos.value
    }

}