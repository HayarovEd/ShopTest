package com.edurda77.product.presentation

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.domain.R
import com.edurda77.product.databinding.ItemPagerBinding

class PhotoCarouselHolder (private val binding: ItemPagerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind (item: String) {
        Glide.with(this.itemView.context)
            .load(item)
            .into(binding.photoIv)

        binding.root.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus) {
                val anim = AnimationUtils.loadAnimation(view.context, R.anim.scale_in_tv);
                binding.root.startAnimation(anim)
                anim.fillAfter = true
            } else {
                val anim = AnimationUtils.loadAnimation(view.context, R.anim.scale_out_tv);
                binding.root.startAnimation(anim)
                anim.fillAfter = true
            }
        }
    }

}