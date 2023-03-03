package com.edurda77.domain.repository

import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.model.ElementSearch
import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.model.User
import com.edurda77.domain.utils.Resource

interface ShopRepository {

    suspend fun getLogin(user:String, password: String): Resource<String>

    suspend fun insertNewUser(user: User): Resource<String>

    suspend fun deleteUser(user: String)

    suspend fun getLatest() : Resource<List<ElementLatest>>

    suspend fun getFlashSales() : Resource<List<ElementFlashSale>>

    suspend fun getProductByChars() : Resource<List<String>>

    suspend fun getProductDetail() : Resource<ProductDetail>
}