package com.example.project_mobile.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_mobile.databinding.ItemProductBinding

class ProductAdapter(private val listProduct: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = listProduct[position]
        holder.binding.tvProductName.text = product.name
        holder.binding.tvProductPrice.text = product.price

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.binding.ivProduct)
    }

    override fun getItemCount(): Int = listProduct.size
}