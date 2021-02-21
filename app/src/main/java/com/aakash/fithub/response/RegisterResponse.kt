package com.aakash.fithub.response

import com.aakash.fithub.model.User

data class RegisterResponse (
      val success: Boolean? = null,
      val data: User? =null
        )