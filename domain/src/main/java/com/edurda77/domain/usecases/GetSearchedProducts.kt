package com.edurda77.domain.usecases

import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import javax.inject.Inject

class GetSearchedProducts @Inject constructor(private val repo:ShopRepository) {
    suspend operator fun invoke (
        query:String
    ) : Resource<List<String>> {
        return when (val result = repo.getProductByChars()) {
            is Resource.Error -> {
                Resource.Error(result.message?: "An unknown error")
            }
            is Resource.Success -> {
                Resource.Success(
                    if (query.isBlank()) {
                        result.data
                    } else {
                        result.data?.filter {
                            it.contains(query, ignoreCase = true)
                        }
                    }
                )
            }
        }
    }
}