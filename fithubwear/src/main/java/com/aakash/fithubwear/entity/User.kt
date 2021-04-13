package com.aakash.fithubwear.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    val _id: String = "",
    val fname: String? = null,
    val lname: String? = null,
    val image: Int? = null,
    val email: String? = null,
    val password: String? = null,
)