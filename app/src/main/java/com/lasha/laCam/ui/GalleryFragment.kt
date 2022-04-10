package com.lasha.laCam.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lasha.laCam.R
import com.lasha.laCam.data.model.Photo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private lateinit var viewModel: GalleryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        viewModel.getData()
        Log.i("MEWO", viewModel.toString())
        initRecyclerView()
        setupBackBtn()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var data = listOf<Photo>()
        viewModel.allPhotos.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                data = listOf(it[1])
            } else {
                Log.i("Recucler", "LiveData is somehow empty")
            }
        }
        Log.i("Recycler", data.toString())
        val adapter = GalleryViewAdapter(requireContext(), data)
        recyclerView.adapter = adapter
    }

    private fun setupBackBtn(){
        backToCameraBtn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_galleryFragment_to_cameraFragment)
        }
    }
}
