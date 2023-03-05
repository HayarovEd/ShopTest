package com.edurda77.data.remote.dto


import com.google.gson.annotations.SerializedName

data class FlashSalesDto(
    @SerializedName("flash_sale")
    val flashSale: List<FlashSale>
)