package com.edurda77.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edurda77.domain.utils.EMAIL
import com.edurda77.domain.utils.PASSWORD
import com.edurda77.domain.utils.TABLE
import com.edurda77.domain.utils.USER

interface UserDao {

    @Query("SELECT * FROM  $TABLE WHERE $USER = :user AND $PASSWORD = :password")
    suspend fun getLogin(user: String, password: String): UserEntitty?

    @Query("SELECT * FROM  $TABLE WHERE $USER = :user AND $PASSWORD = :password")
    suspend fun getContentUser(user: String): UserEntitty?

    @Query("SELECT * FROM  $TABLE WHERE $USER = :user OR $EMAIL = :email")
    suspend fun getContentUserData (user: String, email: String): UserEntitty?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntitty)

    @Delete
    suspend fun deleteUser(user: UserEntitty)
}