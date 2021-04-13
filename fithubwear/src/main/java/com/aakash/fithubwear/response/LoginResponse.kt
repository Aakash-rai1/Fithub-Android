package com.aakash.fithubwear.response

import com.aakash.fithubwear.entity.User

data class LoginResponse (
    val success:Boolean?=null,
    val token:String?=null,
    val id:String?=null,
    val data: User?=null,
    val message:String?=null
)
