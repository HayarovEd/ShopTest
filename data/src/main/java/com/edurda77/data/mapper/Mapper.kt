package com.edurda77.data.mapper

import com.edurda77.data.local.UserEntitty
import com.edurda77.domain.model.User

fun UserEntitty.convertToUser(): User {
    return User(
        id = this.id,
        user = this.user,
        password = this.password,
        email = this.email
    )
}

fun List<UserEntitty>.convertToUsers(): List<User> {
    return this.map {
        User(
            id = it.id,
            user = it.user,
            password = it.password,
            email = it.email
        )
    }
}

fun User.convertToUserEntity() : UserEntitty {
    return UserEntitty(
        user = this.user,
        password = this.password,
        email = this.email
    )
}