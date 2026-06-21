package com.example.project_mobile.data.api

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_mobile.data.model.NewsPost
import com.example.project_mobile.databinding.ItemNewsHorizontalBinding

class NewsAdapter(
    private val listNews: List<NewsPost>,
    private val isVertical: Boolean = false
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNewsHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        
        // Mode Vertikal: Paksa lebar match_parent agar rapi dalam list vertikal
        if (isVertical) {
            val params = binding.root.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            binding.root.layoutParams = params
        }
        
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = listNews[position]
        holder.binding.tvNewsTitle.text = news.title ?: "No Title"
        holder.binding.tvNewsDate.text = news.isoDate ?: ""

        // Fallback gambar: image -> thumbnail
        val imageUrl = if (!news.image.isNullOrEmpty()) news.image else news.thumbnail

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(com.example.project_mobile.R.drawable.logo)
            .error(com.example.project_mobile.R.drawable.logo)
            .centerCrop()
            .into(holder.binding.ivNewsThumbnail)

        holder.itemView.setOnClickListener {
            news.link?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listNews.size
}