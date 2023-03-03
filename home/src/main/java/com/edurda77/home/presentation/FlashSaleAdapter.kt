package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.home.databinding.ItemCategoryBinding
import com.edurda77.home.databinding.ItemFlashSalesBinding
import com.edurda77.home.databinding.ItemLatestBinding

class FlashSaleAdapter (
    private val dataList: List<ElementFlashSale>
) : RecyclerView.Adapter<FlashSaleHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FlashSaleHolder(ItemFlashSalesBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: FlashSaleHolder, position: Int) {
        val positionInList = position % dataList.size
        val item: ElementFlashSale = dataList[positionInList]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}