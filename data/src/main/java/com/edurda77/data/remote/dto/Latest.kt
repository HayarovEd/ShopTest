package com.edurda77.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Latest(
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)