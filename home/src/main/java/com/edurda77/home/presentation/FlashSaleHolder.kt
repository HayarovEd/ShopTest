package com.edurda77.home.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.home.databinding.ItemFlashSalesBinding

class FlashSaleHolder (private val binding: ItemFlashSalesBinding) :
    RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("SetTextI18n")
    fun bind (item: ElementFlashSale) {
        binding.salesTv.text = "${item.discount}% off"
        binding.categoryItemTv.text = item.category
        binding.modelTv.text = item.name
        binding.priceTv.text = "$ ${item.price}"
        Glide.with(this.itemView.context)
            .load(item.imageUrl)
            .into(binding.photoIv)
    }
}