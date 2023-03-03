package com.edurda77.home.presentation

import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.databinding.ItemBrandBinding

class BrandHolder (private val binding: ItemBrandBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: Int) {
        binding.photoIv.setImageResource(item)
    }
}