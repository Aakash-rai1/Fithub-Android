package com.aakash.fithub.repository

import com.aakash.fithub.api.MyApiRequest
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.response.WorkOutResponse

class WorkOutRepository : MyApiRequest(){
    private val WorkoutApi = ServiceBuilder.buildServices(com.aakash.fithub.api.WorkoutApi::class.java)

    suspend fun  getWorkOutApiData(): WorkOutResponse{
        return apiRequest {
            WorkoutApi.addWorkout(ServiceBuilder.token!!)
        }
    }
}