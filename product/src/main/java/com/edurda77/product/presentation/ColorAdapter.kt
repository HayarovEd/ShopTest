package com.edurda77.product.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.product.databinding.ItemColorBinding

class ColorAdapter(private val colorList: List<String>,
                   private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<ColorHolder>() {

    interface OnStateClickListener {
        fun onStateClick(item: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorHolder(ItemColorBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        val item: String = colorList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

}