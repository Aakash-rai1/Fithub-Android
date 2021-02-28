package com.aakash.fithub.api

import com.aakash.fithub.entity.Workout
import com.aakash.fithub.response.ImageResponse
import com.aakash.fithub.response.WorkOutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface WorkoutApi {

    @GET("workout/display")
    suspend fun addWorkout(
      @Header("Authorization") token: String
    ):Response<WorkOutResponse>
}