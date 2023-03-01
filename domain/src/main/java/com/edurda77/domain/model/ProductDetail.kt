package com.edurda77.domain.model

data class ProductDetail(
    val colors: List<String>,
    val description: String,
    val imageUrls: List<String>,
    val name: String,
    val numberOfReviews: Int,
    val price: Int,
    val rating: Double
)
