package com.aakash.fithub.api

import com.aakash.fithub.entity.User
import com.aakash.fithub.response.ImageResponse
import com.aakash.fithub.response.LoginResponse
import com.aakash.fithub.response.RegisterResponse
import com.aakash.fithub.response.UserUpdateResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface
UserApi {

    @POST("user/add")
    suspend fun userAdd(@Body users: User):Response<RegisterResponse>


    @POST("user/login")
    suspend fun checkUser(
            @Body users: User
    ):Response<LoginResponse>

    @PUT("user/updatea")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Body user: User

    ): Response<UserUpdateResponse>

    @GET("user/single/{id}")
    suspend fun viewUser(
            @Header("Authorization") token: String,
        @Path("id") id: String,
           // @Body user: User
    ): Response<LoginResponse>

    @Multipart
    @PUT("update/Profile/{id}")
    suspend fun  uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id:String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>

}