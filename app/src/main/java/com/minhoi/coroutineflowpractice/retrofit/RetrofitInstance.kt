package com.minhoi.coroutineflowpractice.retrofit

import com.minhoi.coroutineflowpractice.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL = "http://openapi.foodsafetykorea.go.kr/api/" + BuildConfig.API_KEY + "/"

    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit {
        return client
    }
}

