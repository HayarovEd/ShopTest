package com.edurda77.product.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.product.databinding.ItemPhotoProductBinding

class BigPhotoHolder (private val binding: ItemPhotoProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: String) {
        Glide.with(this.itemView.context)
            .load(item)
            .into(binding.photoIv)
    }

}