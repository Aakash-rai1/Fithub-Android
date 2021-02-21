package com.aakash.fithub.api

import com.aakash.fithub.model.User
import com.aakash.fithub.response.LoginResponse
import com.aakash.fithub.response.RegisterResponse
import com.aakash.fithub.response.ViewRepository
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("user/add")
    suspend fun userAdd(@Body users: User):Response<RegisterResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun checkUser(
        @Field("email") email:String,
        @Field("password") password:String
    ):Response<LoginResponse>

}