package com.aakash.fithub.api

import com.aakash.fithub.entity.AddFav
import com.aakash.fithub.response.AddFavResponse
import com.aakash.fithub.response.ForFavproductReponse
import retrofit2.Response
import retrofit2.http.*

interface AddFavApi {
    @GET("fav/profuct/{id}")
    suspend fun getAllFavProduct(
            @Header("Authorization") token:String,
            @Path("id") userId:String
    ): Response<ForFavproductReponse>

    @POST("add/fav")
    suspend fun AddFavtheProduct(
            @Header("Authorization") token:String,
            @Body addFav: AddFav
    ): Response<AddFavResponse>

    @GET("fav/product/by/{id}")
    suspend fun getParticularFavPRoduct(
            @Header("Authorization") token:String,
            @Path("id") userId:String
    ): Response<AddFavResponse>

    @DELETE("delete/bookmark/{PId}")
    suspend fun deleteFavPRoduct(
            @Header("Authorization") token:String,
            @Path("PId") PId:String
    ): Response<AddFavResponse>
}