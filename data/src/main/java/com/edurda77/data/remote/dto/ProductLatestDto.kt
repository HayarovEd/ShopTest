package com.edurda77.data.remote.dto

import com.google.gson.annotations.SerializedName


data class ProductLatestDto(
    @SerializedName("latest")
    val latest: List<Latest>
)