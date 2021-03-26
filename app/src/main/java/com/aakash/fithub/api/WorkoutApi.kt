package com.aakash.fithub.api

import com.aakash.fithub.response.WorkOutResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WorkoutApi {

    @GET("workout/display")
    suspend fun addWorkout(
      @Header("Authorization") token: String
    ):Response<WorkOutResponse>
}