package com.edurda77.product.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.product.databinding.ItemPhotoProductBinding

class BigPhotoAdapter(private val carouselDataList: List<String>,
                      private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<BigPhotoHolder>() {

    interface OnStateClickListener {
        fun onStateClick(item: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigPhotoHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BigPhotoHolder(ItemPhotoProductBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: BigPhotoHolder, position: Int) {
        val item: String = carouselDataList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

}