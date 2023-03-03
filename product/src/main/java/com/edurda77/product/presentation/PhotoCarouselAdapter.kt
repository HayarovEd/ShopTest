package com.edurda77.product.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.product.databinding.ItemPagerBinding

class PhotoCarouselAdapter(private val carouselDataList: List<String>) :
    RecyclerView.Adapter<PhotoCarouselHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCarouselHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PhotoCarouselHolder(ItemPagerBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: PhotoCarouselHolder, position: Int) {
        val item: String = carouselDataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

}