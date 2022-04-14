package com.lasha.laCam.ui.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lasha.laCam.R
import com.lasha.laCam.data.model.Photo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class GalleryViewAdapter: RecyclerView.Adapter<GalleryViewAdapter.ViewHolder>() {
    private var dbList = ArrayList<Photo>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.db_view, parent, false)
        return ViewHolder(view)
    }

    fun updateGalleryInfo(newInfo: ArrayList<Photo>){
        dbList = newInfo
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = dbList[position]
        holder.fileName.text = ItemsViewModel.fileName
        holder.filePath.text = ItemsViewModel.filePath
        Picasso.get()
            .load(ItemsViewModel.fileUri)
            .error(com.google.android.material.R.drawable.abc_btn_check_material)
            .resize(300,400)
            .centerCrop()
            .into(holder.imageView)
        Log.i("ImagePath:","${ItemsViewModel.filePath}/${ItemsViewModel.fileName}.jpg")
    }

    override fun getItemCount(): Int {
        return dbList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fileName: TextView = itemView.findViewById(R.id.fileName)
        val filePath: TextView = itemView.findViewById(R.id.filePath)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
    }

}