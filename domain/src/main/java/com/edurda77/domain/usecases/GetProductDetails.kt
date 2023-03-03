package com.edurda77.domain.usecases

import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import javax.inject.Inject

class GetProductDetails @Inject constructor(private val repo: ShopRepository) {
    suspend operator fun invoke (
    ) : Resource<ProductDetail> {
        return when (val result = repo.getProductDetail()) {
            is Resource.Error -> {
                Resource.Error(result.message?: "An unknown error")
            }
            is Resource.Success -> {
                Resource.Success(result.data)
            }
        }
    }
}