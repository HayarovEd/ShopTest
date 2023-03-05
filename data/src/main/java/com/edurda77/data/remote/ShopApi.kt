package com.edurda77.data.remote

import com.edurda77.data.remote.dto.FlashSalesDto
import com.edurda77.data.remote.dto.ProductDetailsDto
import com.edurda77.data.remote.dto.ProductLatestDto
import com.edurda77.data.remote.dto.WordsDto
import retrofit2.http.GET

interface ShopApi {
    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSales(
    ) : FlashSalesDto

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(
    ) : ProductLatestDto

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getProductDetails(
    ) : ProductDetailsDto

    @GET("4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun searchByChar(
    ) : WordsDto
}