package com.aakash.fithub.repository

import com.aakash.fithub.api.AddFavApi
import com.aakash.fithub.api.MyApiRequest
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.AddFav
import com.aakash.fithub.response.AddFavResponse
import com.aakash.fithub.response.ForFavproductReponse

class AddFavrepository: MyApiRequest(){
    val myApi= ServiceBuilder.buildServices(AddFavApi::class.java)
    suspend fun getallFavProdcut(id:String): ForFavproductReponse {
        return apiRequest {
            myApi.getAllFavProduct(ServiceBuilder.token!!,id)
        }
    }
    suspend fun AddFav(addFav: AddFav): AddFavResponse {
        return apiRequest {
            myApi.AddFavtheProduct(ServiceBuilder.token!!,addFav)
        }
    }
    suspend fun getParticularNote():AddFavResponse{
        return apiRequest {
            myApi.getParticularFavPRoduct(ServiceBuilder.token!!,ServiceBuilder.id!!)
        }
    }
    suspend fun deleteFavProduct(noteId:String):AddFavResponse{
        return apiRequest {
            myApi.deleteFavPRoduct(ServiceBuilder.token!!,noteId)
        }
    }
}