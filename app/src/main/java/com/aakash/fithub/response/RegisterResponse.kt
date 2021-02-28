package com.aakash.fithub.response

import com.aakash.fithub.entity.User


data class RegisterResponse (
      val success: Boolean? = null,
      val data: User? =null
        )