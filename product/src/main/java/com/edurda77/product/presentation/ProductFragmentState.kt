package com.edurda77.product.presentation

import com.edurda77.domain.model.ProductDetail

sealed class ProductFragmentState {
    object Loading: ProductFragmentState()
    class Error(val error:String): ProductFragmentState()
    class Success (
        val data: ProductDetail,
        val position: Int = 0
    ): ProductFragmentState()
}