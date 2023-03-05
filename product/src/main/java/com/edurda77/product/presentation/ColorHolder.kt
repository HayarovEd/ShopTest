package com.edurda77.product.presentation

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.product.databinding.ItemColorBinding

class ColorHolder (private val binding: ItemColorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: String) {
        binding.colorIv.setBackgroundColor(Color.parseColor(item))
    }

}