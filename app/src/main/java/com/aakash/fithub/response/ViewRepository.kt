package com.aakash.fithub.response

import com.aakash.fithub.entity.User


data class ViewRepository (
    val success:Boolean?=null,
    val list:List<User>?=null
)