package com.edurda77.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.home.databinding.ItemCategoryBinding

class CategoryAdapter (
    private val dataList: List<Category>
) : RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(ItemCategoryBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val positionInList = position % dataList.size
        val category: Category = dataList[positionInList]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            Integer.MAX_VALUE
        } else 0
    }
}