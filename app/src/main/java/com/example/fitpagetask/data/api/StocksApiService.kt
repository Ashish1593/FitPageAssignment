package com.example.fitpagetask.data.api

import com.example.fitpagetask.data.model.Stocks
import retrofit2.http.GET

interface StocksApiService {
    @GET("data")
    suspend fun getStocks(): List<Stocks>
}

