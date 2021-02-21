package com.aakash.fithub.api

import com.aakash.fithub.model.User
import com.aakash.fithub.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AdminApi {
    @POST("user/add")
    suspend fun userAdd(@Body users: User): Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUSer(
        @Field("uname")  uname:String,
        @Field("password") paword:String,
    ): Response<LoginResponse>
}
