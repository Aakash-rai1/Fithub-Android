package com.aakash.fithub.api

import com.aakash.fithub.entity.User
import com.aakash.fithub.response.LoginResponse
import com.aakash.fithub.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface
UserApi {

    @POST("user/add")
    suspend fun userAdd(@Body users: User):Response<RegisterResponse>


    @POST("user/login")
    suspend fun checkUser(
            @Body users: User
//        @Field("email") email:String,
//        @Field("password") password:String
    ):Response<LoginResponse>

}