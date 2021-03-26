package com.aakash.fithub.repository

import api.MyApiRequest
import api.ServiceBuilder
import com.aakash.fithub.response.WorkOutResponse

class WorkOutRepository : MyApiRequest(){
    private val WorkoutApi = ServiceBuilder.buildServices(api.WorkoutApi::class.java)

    suspend fun  getWorkOutApiData(): WorkOutResponse{
        return apiRequest {
            WorkoutApi.addWorkout(ServiceBuilder.token!!)
        }
    }
}