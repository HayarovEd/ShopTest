package com.edurda77.data.repository

import com.edurda77.data.local.UserDao
import com.edurda77.data.mapper.convertToUser
import com.edurda77.data.mapper.convertToUserEntity
import com.edurda77.domain.model.User
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(private val dao: UserDao) : ShopRepository {

    override suspend fun getLogin(user: String, password: String): Resource<User> {
        val contentUser = dao.getContentUser(user = user)
        return try {
            if (contentUser == null) {
                Resource.Error("User is not database")
            } else {
                val validPassword = dao.getLogin(user = user, password = password)
                if (validPassword == null) {
                    Resource.Error("password incorrect")
                } else {
                    Resource.Success(validPassword.convertToUser())
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

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.convertToUserEntity())
    }
}