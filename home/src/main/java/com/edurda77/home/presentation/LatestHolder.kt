package com.edurda77.home.presentation

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementLatest
import com.edurda77.home.databinding.ItemCategoryBinding
import com.edurda77.home.databinding.ItemLatestBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LatestHolder (private val binding: ItemLatestBinding) :
    RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("SetTextI18n")
    fun bind (item: ElementLatest) {
        binding.categoryItemTv.text = item.category
        binding.modelTv.text = item.name
        binding.priceTv.text = "$ ${item.price}"
        Glide.with(this.itemView.context)
            .load(item.imageUrl)
            .into(binding.photoIv)
    }
}