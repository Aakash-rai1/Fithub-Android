
package com.aakash.fithub.repository

import com.aakash.fithub.api.MyApiRequest
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.api.UserApi
import com.aakash.fithub.entity.User
import com.aakash.fithub.response.LoginResponse
import com.aakash.fithub.response.RegisterResponse

class UserRepository: MyApiRequest() {
    val myApi= ServiceBuilder.buildServices(UserApi::class.java)

    suspend fun registerUSer(user: User):RegisterResponse{
        return apiRequest {
            myApi.userAdd(user)
        }
    }
    suspend fun checkUser(user: User):LoginResponse{
        return apiRequest {
            myApi.checkUser(user)
        }
    }
}
