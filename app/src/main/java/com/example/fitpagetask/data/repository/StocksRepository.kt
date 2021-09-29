package com.example.fitpagetask.data.repository

import com.example.fitpagetask.data.api.StocksApiService
import com.example.fitpagetask.data.model.Stocks
import com.example.fitpagetask.utils.Resource
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class StocksRepository(private val apiService: StocksApiService) {
    suspend fun getStocks(): Flow<Resource<List<Stocks>>> {
        return flow {
            val stocks=apiService.getStocks()
            emit(Resource.success(stocks))
        }.flowOn(Dispatchers.IO)
    }
}
