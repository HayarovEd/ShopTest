package com.edurda77.home.presentation

import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.home.databinding.ItemCategoryBinding

class CategoryHolder (private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: Category) {
        binding.categoryIv.setImageResource(item.imageInt)
        binding.categoryTv.setText(item.nameInt)
    }
}