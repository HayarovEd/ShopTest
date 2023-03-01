package com.edurda77.data.mapper

import com.edurda77.data.local.UserEntitty
import com.edurda77.data.remote.dto.FlashSalesDto
import com.edurda77.data.remote.dto.ProductDetailsDto
import com.edurda77.data.remote.dto.ProductLatestDto
import com.edurda77.data.remote.dto.WordsDto
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.model.ElementSearch
import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.model.User

fun User.convertToUserEntity(): UserEntitty {
    return UserEntitty(
        user = this.user,
        password = this.password,
        email = this.email
    )
}

fun WordsDto.convertToElementsSearch(): List<ElementSearch> {
    return this.words.map {
        ElementSearch(name = it)
    }
}

fun ProductLatestDto.convertToElementsLatest(): List<ElementLatest> {
    return this.latest.map { latest ->
        ElementLatest(
            name = latest.name,
            category = latest.category,
            imageUrl = latest.imageUrl,
            price = latest.price
        )
    }
}

fun FlashSalesDto.convertToElementsFlashSales() : List<ElementFlashSale> {
    return this.flashSale.map { flashSale ->
        ElementFlashSale(
            name = flashSale.name,
            category = flashSale.category,
            imageUrl = flashSale.imageUrl,
            price = flashSale.price,
            discount = flashSale.discount
        )
    }
}

fun ProductDetailsDto.convertToProductDetail () : ProductDetail {
    return ProductDetail(
        colors = this.colors,
        imageUrls = this.imageUrls,
        description = this.description,
        name = this.name,
        numberOfReviews = this.numberOfReviews,
        rating = this.rating,
        price = this.price
    )
}