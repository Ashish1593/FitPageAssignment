package com.example.fitpagetask.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fitpagetask.data.repository.StocksRepository
import com.example.fitpagetask.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val stocksRepository: StocksRepository) : ViewModel() {

    fun getStocks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = stocksRepository.getStocks()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}