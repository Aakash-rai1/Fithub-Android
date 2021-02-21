package com.aakash.fithub.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL ="http://10.0.2.2:1500/"
    var token: String?= null
    private val okhttp= OkHttpClient.Builder()
    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofitBuilder= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okhttp.build())

    private val retrofit= retrofitBuilder.build()

    fun <T> buildServices(serviceType: Class<T>):T{
        return retrofit.create(serviceType)
    }
}