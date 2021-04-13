package com.aakash.fithubwear.api

import com.aakash.fithubwear.entity.User
import com.aakash.fithubwear.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface
UserApi {


    @POST("user/login")
    suspend fun checkUser(
        @Body users: User
    ):Response<LoginResponse>



    @GET("user/single/{id}")
    suspend fun viewUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        // @Body user: User
    ): Response<LoginResponse>





}