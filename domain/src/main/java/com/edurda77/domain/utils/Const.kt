package com.edurda77.domain.utils

import com.edurda77.domain.R
import com.edurda77.domain.model.Category

const val USER_DB = "user_db"
const val TABLE = "users"
const val ID = "id"
const val USER = "user"
const val PASSWORD = "password"
const val EMAIL = "email"
const val USERNAME = "username"
const val PICK_IMAGE_REQUEST = 1
const val BASE_URL = "https://run.mocky.io/v3/"

val categoriesList = listOf(
    Category(
        nameInt = R.string.phones,
        imageInt = R.drawable.ic_phones
    ),
    Category(
        nameInt = R.string.headphones,
        imageInt = R.drawable.ic_headphones
    ),
    Category(
        nameInt = R.string.games,
        imageInt = R.drawable.ic_games
    ),
    Category(
        nameInt = R.string.cars,
        imageInt = R.drawable.ic_cars
    ),
    Category(
        nameInt = R.string.furniture,
        imageInt = R.drawable.ic_furniture
    ),
    Category(
        nameInt = R.string.kids,
        imageInt = R.drawable.ic_kids
    )

)

val brands = listOf(
    R.drawable.shape_round_corner,
    R.drawable.shape_round_corner,
    R.drawable.shape_round_corner,
    R.drawable.shape_round_corner,
)