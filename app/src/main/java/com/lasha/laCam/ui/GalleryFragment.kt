package com.lasha.laCam.ui

import androidx.fragment.app.Fragment
import androidx.room.Room
import com.lasha.laCam.R
import com.lasha.laCam.data.db.GalleryDataBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    val db = Room.databaseBuilder(
        requireContext(),
        GalleryDataBase::class.java,
        "DataBase"
    ).build()
}
