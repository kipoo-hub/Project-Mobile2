package com.example.project_mobile.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.project_mobile.databinding.ItemMessageBinding

class MessageAdapter(context: Context, private val listMessage: List<MessageModel>) :
    ArrayAdapter<MessageModel>(context, 0, listMessage) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding
        val view: View

        if (convertView == null) {
            binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ItemMessageBinding
        }

        val item = listMessage[position]

        binding.tvMessageName.text = item.name
        binding.tvMessagePreview.text = item.message

        Glide.with(context)
            .load(item.image)
            .circleCrop()
            .into(binding.ivMessageUser)

        return view
    }
}