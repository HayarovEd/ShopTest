package com.edurda77.domain.usecases

import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import javax.inject.Inject

class GetFlashSaleProducts @Inject constructor(private val repo: ShopRepository) {
    suspend operator fun invoke (
    ) : Resource<List<ElementFlashSale>> {
        return when (val result = repo.getFlashSales()) {
            is Resource.Error -> {
                Resource.Error(result.message?: "An unknown error")
            }
            is Resource.Success -> {
                Resource.Success(result.data)
            }
        }
    }
}