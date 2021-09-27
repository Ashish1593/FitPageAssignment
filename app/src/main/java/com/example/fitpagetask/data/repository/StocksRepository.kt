package com.example.fitpagetask.data.repository

import com.example.fitpagetask.data.api.ApiHelper

class StocksRepository(private val apiHelper: ApiHelper) {

    suspend fun getStocks() = apiHelper.getStocks()
}