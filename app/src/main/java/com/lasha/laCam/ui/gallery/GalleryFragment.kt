package com.lasha.laCam.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lasha.laCam.R
import com.lasha.laCam.data.model.Photo
import com.lasha.laCam.ui.camera.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private lateinit var viewModel: GalleryViewModel
    private val adapter = GalleryViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupBackBtn()
        initRecyclerView()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        viewModel.allPhotos.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                adapter.updateGalleryInfo(it as ArrayList<Photo>)
                Log.i("GalleryObserverDone", "ddddd")
            } else {
                Log.i("GalleryObserverError", "Data either null or empty")
            }
        }
    }

    private fun setupBackBtn(){
        backToCameraBtn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_galleryFragment_to_cameraFragment)
        }
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}
