package com.aakash.fithub.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = false)
        val _id: String = "",
        val fname: String? = null,
        val lname: String? = null,
        val age: Int? = null,
        val gender: String? = null,
        val height: String? = null,
        val weight: String?= null,
        val email: String? = null,
        val password: String? = null,
)