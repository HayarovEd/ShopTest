package com.edurda77.domain.repository

import com.edurda77.domain.model.User
import com.edurda77.domain.utils.Resource

interface ShopRepository {

    suspend fun getLogin(user:String, password: String): Resource<User>

    suspend fun insertNewUser(user: User): Resource<String>

    suspend fun deleteUser(user: User)
}