
package com.aakash.fithub.repository

import api.MyApiRequest
import api.ServiceBuilder
import api.UserApi
import com.aakash.fithub.entity.User
import com.aakash.fithub.response.ImageResponse
import com.aakash.fithub.response.LoginResponse
import com.aakash.fithub.response.RegisterResponse
import com.aakash.fithub.response.UserUpdateResponse
import okhttp3.MultipartBody

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

    suspend fun  updateUser( user: User): UserUpdateResponse{
        return apiRequest {
            myApi.updateUser(ServiceBuilder.token!!,user)
        }
    }

    suspend fun getUser( id:String): LoginResponse{
        return apiRequest {
            myApi.viewUser(ServiceBuilder.token!!,id)
        }
    }

    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            myApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }
}
