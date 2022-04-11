package com.lasha.laCam.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lasha.laCam.R
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private lateinit var viewModel: CameraViewModel
    private val data = ArrayList<Photo>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CameraViewModel::class.java]
        Log.i("MEWO", viewModel.toString())
        initData()
//        checkPermissions()
        setupBackBtn()
        initRecyclerView(data)
    }

    private fun initData(){
        viewModel.getPhotoData()
        viewModel.allPhotos.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                for (element in 0..it.lastIndex)
                {
                    data.add(it[element])
                }
            } else {
                Log.i("AA", "Data either null or empty")
            }
        }
        Log.i("AA", data.toString())
    }

    private fun setupBackBtn(){
        backToCameraBtn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_galleryFragment_to_cameraFragment)
        }
    }

    private fun initRecyclerView(data: ArrayList<Photo>){

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = GalleryViewAdapter(requireContext(), data)
        recyclerView.adapter = adapter
    }

//    private fun checkPermissions(){
//        if (!allPermissionsGranted()){
//            ActivityCompat.requestPermissions(requireActivity(),
//                arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_PERMISSIONS
//            );
//        } else {
//
//        }
//    }
//
//    private fun allPermissionsGranted() = REQUIRED_GALLERY_PERMISSIONS.all {
//        ContextCompat.checkSelfPermission(
//            requireContext(), it) == PackageManager.PERMISSION_GRANTED
//    }
//    companion object {
//        private const val TAG = "Gallery"
//        private const val REQUEST_CODE_PERMISSIONS = 101
//        private val REQUIRED_GALLERY_PERMISSIONS =
//            mutableListOf (
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ).apply {
//            }.toTypedArray()
//    }
}
