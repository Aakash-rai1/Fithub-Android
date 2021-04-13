package com.aakash.fithubwear.Repository

import com.aakash.fithubwear.api.MyApiRequest
import com.aakash.fithubwear.api.ServiceBuilder
import com.aakash.fithubwear.api.UserApi
import com.aakash.fithubwear.entity.User
import com.aakash.fithubwear.response.LoginResponse
import okhttp3.MultipartBody

class UserRepository: MyApiRequest() {
    val myApi= ServiceBuilder.buildServices(UserApi::class.java)




    suspend fun checkUser(user: User):LoginResponse{
        return apiRequest {
            myApi.checkUser(user)
        }
    }

//
//
//    suspend fun getUser( id:String): LoginResponse{
//        return apiRequest {
//            myApi.viewUser(ServiceBuilder.token!!,id)
//        }
//    }




}
