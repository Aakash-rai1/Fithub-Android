package com.aakash.fithub.api

import com.aakash.fithub.response.WorkOutResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface WorkoutApi {

    @GET("workout/display")
    suspend fun addWorkout(
      @Header("Authorization") token: String
    ):Response<WorkOutResponse>


    @GET("workout/display")
    suspend fun getallWorkout(
            @Header("Authorization") token: String,
            @Path("id") userId:String
    ):Response<WorkOutResponse>

}