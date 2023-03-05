package com.edurda77.data.repository

import com.edurda77.data.local.UserDao
import com.edurda77.data.mapper.convertToElementsFlashSales
import com.edurda77.data.mapper.convertToElementsLatest
import com.edurda77.data.mapper.convertToElementsSearch
import com.edurda77.data.mapper.convertToListString
import com.edurda77.data.mapper.convertToProductDetail
import com.edurda77.data.mapper.convertToUserEntity
import com.edurda77.data.remote.ShopApi
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.model.ElementSearch
import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.model.User
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val shopApi: ShopApi
) : ShopRepository {

    override suspend fun getLogin(user: String, password: String): Resource<String> {
        val contentUser = dao.getContentUser(user = user)
        return try {
            if (contentUser == null) {
                Resource.Error("User is not database")
            } else {
                val validPassword = dao.getLogin(user = user, password = password)
                if (validPassword == null) {
                    Resource.Error("password incorrect")
                } else {
                    Resource.Success("Ok")
                }
            }
        } catch (error: java.lang.Exception) {
            Resource.Error("unknown error")
        }
    }

    override suspend fun insertNewUser(user: User): Resource<String> {
        val request = dao.getContentUserData(user = user.user, email = user.email)
        return try {
            if (request == null) {
                dao.insertUser(user.convertToUserEntity())
                Resource.Success("$user add in database")
            } else {
                Resource.Error("$user already exist")
            }
        } catch (error: java.lang.Exception) {
            Resource.Error("unknown error")
        }
    }

    override suspend fun deleteUser(user: String) {
        dao.deleteUser(user)
    }

    override suspend fun getLatest(): Resource<List<ElementLatest>> {
        return try {
            Resource.Success(
                data = shopApi.getLatest().convertToElementsLatest()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }

    }

    override suspend fun getFlashSales(): Resource<List<ElementFlashSale>> {
        return try {
            Resource.Success(
                data = shopApi.getFlashSales().convertToElementsFlashSales()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }

    override suspend fun getProductByChars(): Resource<List<String>> {
        return try {
            Resource.Success(
                data = shopApi.searchByChar().convertToListString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }

    override suspend fun getProductDetail(): Resource<ProductDetail> {
        return try {
            Resource.Success(
                data = shopApi.getProductDetails().convertToProductDetail()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}