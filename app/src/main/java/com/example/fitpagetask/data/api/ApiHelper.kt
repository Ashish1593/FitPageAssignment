package com.example.fitpagetask.data.api

class ApiHelper(private val apiService: StocksApiService) {

    suspend fun getStocks() = apiService.getStocks()
}