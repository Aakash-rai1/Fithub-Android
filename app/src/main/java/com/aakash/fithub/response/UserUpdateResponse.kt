package com.aakash.fithub.response

import com.aakash.fithub.entity.User

data class UserUpdateResponse (
    val success: Boolean? = null,
    val data: User? = null,
val id: String?=null
        )