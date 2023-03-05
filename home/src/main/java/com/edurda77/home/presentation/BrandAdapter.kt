package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.home.databinding.ItemBrandBinding

class BrandAdapter (
    private val dataList: List<Int>
) : RecyclerView.Adapter<BrandHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BrandHolder(ItemBrandBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: BrandHolder, position: Int) {
        val positionInList = position % dataList.size
        val item: Int = dataList[positionInList]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}