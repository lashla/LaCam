package com.lasha.laCam.ui

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lasha.laCam.R
import com.lasha.laCam.data.model.Photo
import com.squareup.picasso.Picasso
import java.io.File
import javax.inject.Inject

class GalleryViewAdapter@Inject constructor(private val context: Context, private val mList: List<Photo>): RecyclerView.Adapter<GalleryViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.db_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.fileName.text = ItemsViewModel.fileName
        holder.filePath.text = ItemsViewModel.filePath
        Picasso.get()
            .load(ItemsViewModel.fileUri)
            .error(com.google.android.material.R.drawable.abc_btn_check_material)
            .into(holder.imageView)
        Log.i("ImagePath:","${ItemsViewModel.filePath}/${ItemsViewModel.fileName}.jpg")
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fileName: TextView = itemView.findViewById(R.id.fileName)
        val filePath: TextView = itemView.findViewById(R.id.filePath)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
    }

}