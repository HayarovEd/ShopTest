package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementLatest
import com.edurda77.home.databinding.ItemCategoryBinding
import com.edurda77.home.databinding.ItemLatestBinding

class LatestAdapter (
    private val dataList: List<ElementLatest>
) : RecyclerView.Adapter<LatestHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LatestHolder(ItemLatestBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: LatestHolder, position: Int) {
        val positionInList = position % dataList.size
        val item: ElementLatest = dataList[positionInList]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}