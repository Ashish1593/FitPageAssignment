package com.example.fitpagetask.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "https://mobile-app-challenge.herokuapp.com/"
    fun apiService(): StocksApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(StocksApiService::class.java)
}
