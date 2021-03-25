package com.aakash.fithub.response

import com.aakash.fithub.entity.User

data class LoginResponse (
    val success:Boolean?=null,
    val token:String?=null,
    val id:String?=null,
    val data:User?=null,
    val message:String?=null
)