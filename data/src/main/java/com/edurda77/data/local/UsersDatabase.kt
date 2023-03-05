package com.edurda77.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edurda77.domain.utils.USER_DB

@Database(
    entities = [UserEntitty::class],
    version = 1
)
abstract class UsersDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = USER_DB
    }
}