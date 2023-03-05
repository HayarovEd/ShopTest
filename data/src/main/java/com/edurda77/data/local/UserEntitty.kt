package com.edurda77.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edurda77.domain.utils.EMAIL
import com.edurda77.domain.utils.ID
import com.edurda77.domain.utils.PASSWORD
import com.edurda77.domain.utils.TABLE
import com.edurda77.domain.utils.USER

@Entity (tableName = TABLE)
data class UserEntitty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(ID)
    val id: Int = 0,
    @ColumnInfo(USER)
    val user: String,
    @ColumnInfo(PASSWORD)
    val password: String,
    @ColumnInfo(EMAIL)
    val email: String
)