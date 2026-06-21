package com.example.project_mobile.Home.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_mobile.data.model.PhotoResponse
import com.example.project_mobile.databinding.ItemPhotoBinding

class PhotoAdapter(private val listPhotos: List<PhotoResponse>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = listPhotos[position]
        holder.binding.tvAuthor.text = photo.author

        Glide.with(holder.itemView.context)
            .load(photo.downloadUrl)
            .into(holder.binding.ivPhoto)
    }

    override fun getItemCount(): Int = listPhotos.size
}