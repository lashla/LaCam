package com.lasha.laCam.ui

import androidx.lifecycle.*
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {


}