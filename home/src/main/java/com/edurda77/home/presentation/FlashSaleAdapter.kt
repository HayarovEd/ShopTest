package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.home.databinding.ItemFlashSalesBinding

class FlashSaleAdapter (
    private val dataList: List<ElementFlashSale>,
    private val onClickListener: OnStateClickListener
) : RecyclerView.Adapter<FlashSaleHolder>() {

    interface OnStateClickListener {
        fun onStateClick(item:ElementFlashSale, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FlashSaleHolder(ItemFlashSalesBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: FlashSaleHolder, position: Int) {
        val positionInList = position % dataList.size
        val item: ElementFlashSale = dataList[positionInList]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}