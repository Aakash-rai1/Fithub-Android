package com.aakash.fithub.response

import com.aakash.fithub.model.User

data class ViewRepository (
    val success:Boolean?=null,
    val list:List<User>?=null
)